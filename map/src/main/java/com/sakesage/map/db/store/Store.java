package com.sakesage.map.db.store;

import com.sakesage.map.common.entity.BaseEntity;
import com.sakesage.map.db.store.enums.StoreCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@SuperBuilder
public class Store extends BaseEntity {

    @Column
    private String placeCode;

    @Column
    @Enumerated(EnumType.STRING)
    private StoreCategory storeCategory;
}
