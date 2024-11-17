package com.sakesage.map.domain.token.converter;

import com.sakesage.map.common.annotation.Converter;
import com.sakesage.map.common.error.ErrorCode;
import com.sakesage.map.common.exception.ApiException;
import com.sakesage.map.domain.token.controller.model.TokenResponse;
import com.sakesage.map.domain.token.dto.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {
    // converter는 mapper와 유사함
    // 그러나 mapper: 객체 간의 변환 처리
    // converter: 간단한 데이터 타입 간의 변환

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        Objects.requireNonNull(accessToken, ()->{throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken, ()->{throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
