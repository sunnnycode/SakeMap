package com.sakesage.map.domain.product.controller;


import com.sakesage.map.db.product.enums.ProductCategory;
import com.sakesage.map.domain.product.dto.ProductResponse;
import com.sakesage.map.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/product")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class ProductOpenApiController {

    private final ProductService productService;

    // 상품 전체 조회
    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> productResponseList = productService.getAllProducts();
        return ResponseEntity.ok(productResponseList); // 응답 반환

    }

    // 상품 상세 조회
    @GetMapping("/{productCode}")
    public ResponseEntity<ProductResponse> getProductByCode(@PathVariable("productCode") int productCode) {

        ProductResponse productResponse = productService.getProductByCode(productCode);
        if (productResponse != null) {
            return ResponseEntity.ok(productResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 상품 카테고리 상세 조회
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductByCategory(@RequestParam ProductCategory productCategory) {
        List<ProductResponse> productResponseList = productService.getProductByProductCategory(productCategory);
        return ResponseEntity.ok(productResponseList);
    }

}
