package com.sakesage.map.domain.product.service;

import com.sakesage.map.domain.product.dto.ProductRequest;
import com.sakesage.map.domain.product.dto.ProductResponse;

public interface ProductService {

    ProductResponse productRegister(int storeId, String thumbnailUrl, ProductRequest productRequest);
    ProductResponse productUpdate(int storeId, int productCode, String thumbnailUrl, ProductRequest productRequest);
    void productDelete(int storeId, int productCode);

}
