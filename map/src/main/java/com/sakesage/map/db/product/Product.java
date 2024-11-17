package com.sakesage.map.db.product;

import com.sakesage.map.db.product.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@SuperBuilder
@Component
@EqualsAndHashCode(callSuper = false)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private int code;

    @Column(name = "store_id", nullable = false)
    private int storeId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "thumbnail_url", length = 300)
    private String thumbnailUrl;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

}
