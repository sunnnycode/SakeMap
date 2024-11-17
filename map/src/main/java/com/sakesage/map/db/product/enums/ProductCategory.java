package com.sakesage.map.db.product.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductCategory {
    WHISKY("위스키"),
    SAKE("사케")
    ;

    private String description;
}
