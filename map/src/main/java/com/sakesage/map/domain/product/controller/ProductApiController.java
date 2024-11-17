package com.sakesage.map.domain.product.controller;

import com.sakesage.map.domain.product.dto.ProductRequest;
import com.sakesage.map.domain.product.dto.ProductResponse;
import com.sakesage.map.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class ProductApiController {

    private final ProductService productService;

    // 상품 등록
    @PostMapping("/{storeId}")
    public ResponseEntity<ProductResponse> productRegister(
            @PathVariable("storeId") int storeId,
            @RequestParam("thumbnail_url") String thumbnailUrl,
            @RequestBody ProductRequest productRequest
    ) {
        ProductResponse productResponse = productService.productRegister(storeId, thumbnailUrl, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    // 상품 수정
    @PatchMapping("/{storeId}/{productCode}")
    public ResponseEntity<ProductResponse> productUpdate (
            @PathVariable("storeId") int storeId,
            @PathVariable("productCode") int productCode,
            @RequestParam("thumbnail_url") String thumbnailUrl,
            @RequestBody ProductRequest productRequest
    ) {
        ProductResponse productResponse = productService.productUpdate(storeId, productCode, thumbnailUrl, productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }


    // 상품 삭제
    @DeleteMapping("/{storeId}/{productCode}")
    public ResponseEntity<String> productDelete (
            @PathVariable("storeId") int storeId,
            @PathVariable("productCode") int productCode
    ) {
        productService.productDelete(storeId, productCode);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
    }


}
