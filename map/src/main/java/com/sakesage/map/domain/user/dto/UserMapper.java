package com.sakesage.map.domain.user.dto;


import com.sakesage.map.common.annotation.Converter;
import com.sakesage.map.common.error.ErrorCode;
import com.sakesage.map.common.exception.ApiException;
import com.sakesage.map.db.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserMapper {

    public User toEntity(UserRegisterRequest request){

        // Request 데이터 유효성 검사 추가
        if(request == null) {
            throw new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequest Null");
        }

        // 엔티티로 변환
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    public UserResponse toResponse(User user) {

        return Optional.ofNullable(user)
                .map(it -> UserResponse.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .createdAt(user.getCreatedAt())
                        .lastAcceptedAt(user.getLastAcceptedAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserEntity Null"));
    }
}
