package com.sakesage.map.domain.product.dto;

import com.sakesage.map.db.product.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String name;

    private Integer price;

    private String content;

    private ProductCategory productCategory;
}

