package com.sakesage.map.common.interceptor;

import com.sakesage.map.common.error.ErrorCode;
import com.sakesage.map.common.error.TokenErrorCode;
import com.sakesage.map.common.exception.ApiException;
import com.sakesage.map.db.user.UserRepository;
import com.sakesage.map.domain.token.business.TokenBusiness;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;
    private final UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, chrome의 경우 GET, POST OPTIONS
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // js, html, png resource를 요청하는 경우 = pass
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // 헤더 토큰 검증
        var accessToken = request.getHeader("Authorization");
        if (accessToken == null) {
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        // 헤더에서 토큰 가져오기
        var idString = tokenBusiness.validationAccessToken(accessToken); // JWT에서 사용자 ID 추출

        if (idString != null) {
            try {
                int id = Integer.valueOf(idString); // String을 Integer로 변환
                var userOptional = userRepository.findById(id); // ID로 사용자 조회

                if (userOptional.isPresent()) {
                    var userId = userOptional.get().getId(); // 사용자 ID 가져오기
                    var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
                    requestContext.setAttribute("id", userId, RequestAttributes.SCOPE_REQUEST); // 사용자 ID 저장
                    return true;
                } else {
                    throw new ApiException(ErrorCode.BAD_REQUEST, "사용자를 찾을 수 없습니다.");
                }
            } catch (NumberFormatException e) {
                throw new ApiException(ErrorCode.BAD_REQUEST, "유효하지 않은 사용자 ID입니다."); // 예외 처리
            }
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");

    }
}
