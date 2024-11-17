package com.sakesage.map.domain.token.service;

import com.sakesage.map.common.error.ErrorCode;
import com.sakesage.map.common.exception.ApiException;
import com.sakesage.map.domain.token.Ifs.TokenHelperIfs;
import com.sakesage.map.domain.token.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    // 토큰 생성
    public TokenDto issueAccessToken(Integer id){
        var data = new HashMap<String, Object>();
        data.put("id", id);
        return tokenHelperIfs.issueAccessToken(data);

    }

    // 토큰 재발행
    public TokenDto issueRefreshToken(Integer id){
        var data = new HashMap<String, Object>();
        data.put("id", id);
        return tokenHelperIfs.issueAccessToken(data);

    }

    // 토큰 검증
    public String validationToken(String token){
        var map = tokenHelperIfs.validationTokenWithThrow(token);
        var id = map.get("id");
        Objects.requireNonNull(id, () ->{throw new ApiException(ErrorCode.NULL_POINT);});
        return id.toString();

    }

}
