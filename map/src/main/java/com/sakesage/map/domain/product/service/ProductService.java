package com.sakesage.map.domain.product.service;

import com.sakesage.map.db.product.enums.ProductCategory;
import com.sakesage.map.domain.product.dto.ProductRequest;
import com.sakesage.map.domain.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse productRegister(int storeId, String thumbnailUrl, ProductRequest productRequest);
    ProductResponse productUpdate(int storeId, int productCode, String thumbnailUrl, ProductRequest productRequest);
    void productDelete(int storeId, int productCode);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductByCode(int productCode);
    List<ProductResponse> getProductByProductCategory(ProductCategory productCategory);
    List<ProductResponse> getProductByStoreId(int storeId);
}
