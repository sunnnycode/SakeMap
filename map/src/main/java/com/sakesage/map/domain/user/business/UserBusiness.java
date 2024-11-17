package com.sakesage.map.domain.user.business;

import com.sakesage.map.common.annotation.Business;
import com.sakesage.map.domain.token.business.TokenBusiness;
import com.sakesage.map.domain.token.controller.model.TokenResponse;
import com.sakesage.map.domain.user.dto.UserLoginRequest;
import com.sakesage.map.domain.user.dto.UserMapper;
import com.sakesage.map.domain.user.dto.UserRegisterRequest;
import com.sakesage.map.domain.user.dto.UserResponse;
import com.sakesage.map.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    // UserService로 이동 전 로직 처리

    private final UserService userService;
    private final UserMapper userMapper;
    private final TokenBusiness tokenBusiness;

    /**
     * 1. 회원가입(request) -> entity
     * 2. entity -> 데이터베이스에 저장
     * 3. 저장된 엔티티 -> response
     * 4. response 반환
     */
    public UserResponse register(UserRegisterRequest request) {
        var entity = userMapper.toEntity(request);
        var newEntity = userService.register(entity);
        var response = userMapper.toResponse(newEntity);
        return response;
    }

    /**
     * 1. userId, password 를 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성(토큰 비즈니스의 이슈토큰)
     * 4. token response
     */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request);
        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }

}
