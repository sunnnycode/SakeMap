package com.sakesage.map.domain.token.business;

import com.sakesage.map.common.annotation.Business;
import com.sakesage.map.common.error.ErrorCode;
import com.sakesage.map.common.exception.ApiException;
import com.sakesage.map.db.user.User;
import com.sakesage.map.domain.token.controller.model.TokenResponse;
import com.sakesage.map.domain.token.converter.TokenConverter;
import com.sakesage.map.domain.token.service.TokenService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    /**
     * 1. user entity user Id 추출
     * 2. access, refresh token 발행
     * 3. converter -> token response로 변경
     */

    public TokenResponse issueToken(User userEntity){

        return Optional.ofNullable(userEntity)
                .map(user -> {
                    return user.getId();
                })
                .map(user -> {
                    Integer id = userEntity.getId();
                    var accessToken = tokenService.issueAccessToken(id);
                    var refreshToken = tokenService.issueRefreshToken(id);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(
                        ()-> new ApiException(ErrorCode.NULL_POINT)
                );
    }

    public String validationAccessToken(String accessToken){
        var id = tokenService.validationToken(accessToken);
        return id;
    }



}