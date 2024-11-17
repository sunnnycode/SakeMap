package com.sakesage.map.domain.user.service;


import com.sakesage.map.common.error.ErrorCode;
import com.sakesage.map.common.exception.ApiException;
import com.sakesage.map.db.user.User;
import com.sakesage.map.db.user.UserRepository;
import com.sakesage.map.domain.user.dto.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setCreatedAt(LocalDateTime.now());  // createdAt 설정
        return userRepository.save(user);
    }

    public User login(UserLoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST));

        boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new ApiException(ErrorCode.BAD_REQUEST);
        }

        user.setLastAcceptedAt(LocalDateTime.now()); // 로그인 성공 시 lastAcceptedAt 업데이트
        userRepository.save(user);

        return user;
    }

    public User getUserWithThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST));
    }

}

