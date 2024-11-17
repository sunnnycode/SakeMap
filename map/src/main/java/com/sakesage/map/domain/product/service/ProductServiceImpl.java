package com.sakesage.map.domain.product.service;

import com.sakesage.map.db.product.Product;
import com.sakesage.map.db.product.ProductRepository;
import com.sakesage.map.db.store.Store;
import com.sakesage.map.db.store.StoreRepository;
import com.sakesage.map.domain.product.dto.ProductRequest;
import com.sakesage.map.domain.product.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    // 초기화
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, StoreRepository storeRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    // 상품 등록
    public ProductResponse productRegister(int storeId, String thumbnailUrl, ProductRequest productRequest) {

        // storeId에 해당하는 가게 찾기
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isEmpty()) {
            throw new RuntimeException("Store not found with id " + storeId);
        }

        Store store = storeOptional.get();
        Product product = modelMapper.map(productRequest, Product.class);

        product.setStoreId(storeId);
        product.setThumbnailUrl(thumbnailUrl);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    // 상품 수정
    public ProductResponse productUpdate(int storeId, int productCode, String thumbnailUrl, ProductRequest productRequest) {

        // storeId에 해당하는 상품을 찾기
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isEmpty()) {
            throw new RuntimeException("Store not found with id " + storeId);
        }
        Store store = storeOptional.get();

        Product product = productRepository.findById(productCode).orElse(null);
        if (product == null) {
            throw new RuntimeException("Product not found with code: " + productCode);
        }

        // 수정된 데이터로 필드 업데이트
        modelMapper.map(productRequest, product);
        product.setStoreId(storeId);
        product.setThumbnailUrl(thumbnailUrl);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductResponse.class);
    }

    // 상품 삭제
    @Override
    public void productDelete(int storeId, int productCode) {

        // storeId에 해당하는 상품을 찾기
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        if (storeOptional.isEmpty()) {
            throw new RuntimeException("Store not found with id " + storeId);
        }

        Store store = storeOptional.get();

        Product product = productRepository.findById(productCode).orElseThrow(() ->
                new RuntimeException("Product not found with code: " + productCode));

        if (product.getStoreId() != storeId) {
            throw new RuntimeException("Store ID mismatch for product: " + productCode);
        }
        productRepository.delete(product);
    }

}
