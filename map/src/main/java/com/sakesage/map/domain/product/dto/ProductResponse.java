package com.sakesage.map.domain.product.dto;

import com.sakesage.map.db.product.enums.ProductCategory;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private int storeId;

    private int code;

    private String name;

    private Integer price;

    private String thumbnailUrl;

    private String content;

    private ProductCategory productCategory;
}
