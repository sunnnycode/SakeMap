package com.sakesage.map.db.user;

import com.sakesage.map.common.entity.BaseEntity;
import com.sakesage.map.db.user.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@SuperBuilder
@Component
@EqualsAndHashCode(callSuper = false) // Lombok 경고 해결
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role", length = 10)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_accepted_at")
    private LocalDateTime lastAcceptedAt;
}
