package com.sakesage.map.domain.product.service;

import com.sakesage.map.db.product.Product;
import com.sakesage.map.db.product.ProductRepository;
import com.sakesage.map.db.product.enums.ProductCategory;
import com.sakesage.map.db.store.Store;
import com.sakesage.map.db.store.StoreRepository;
import com.sakesage.map.domain.product.dto.ProductRequest;
import com.sakesage.map.domain.product.dto.ProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 전체 상품 조회
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    // 단일 상품 조회
    @Override
    public ProductResponse getProductByCode(int productCode) {
        Product product = productRepository.findById(productCode).orElse(null);

        if (product == null) {
            throw new RuntimeException("Product not found with code: " + productCode);
        }
        return modelMapper.map(product, ProductResponse.class);
    }

    // 카테고리 내 전체 상품 조회
    @Override
    public List<ProductResponse> getProductByProductCategory(ProductCategory productCategory) {
        List<Product> products = productRepository.findByProductCategory(productCategory);

        if (products.isEmpty()) {
            throw new RuntimeException("No products found for category: " + productCategory);
        }

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    // 가게 별 상품 전체 조회
    @Override
    public List<ProductResponse> getProductByStoreId(int storeId) {
        List<Product> products = productRepository.findProductByStoreId(storeId);

        if (products.isEmpty()) {
            throw new RuntimeException("No products found for storeId " + storeId);
        }

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());

    }



}
