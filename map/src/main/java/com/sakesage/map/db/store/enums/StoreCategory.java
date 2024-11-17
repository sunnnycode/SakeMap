package com.sakesage.map.db.store.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {

    WHISKY("위스키"),
    SAKE("사케"),
    ALL("공통")
    ;

    private String description;
}
