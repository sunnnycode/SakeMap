package com.sakesage.map.domain.user.dto;

import com.sakesage.map.db.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private int id;

    private String username;

    private String password;

    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime lastAcceptedAt;
}
