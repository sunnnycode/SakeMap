package com.sakesage.map.db.product;

import com.sakesage.map.db.product.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductCategory(ProductCategory productCategory);

}
