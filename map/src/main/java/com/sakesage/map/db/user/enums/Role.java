package com.sakesage.map.db.user.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {
    MASTER("관리자"),
    CLIENT("고객")
    ;

    private String description;
}
