package com.sakesage.map.db.store;

import com.sakesage.map.common.entity.BaseEntity;
import com.sakesage.map.db.store.enums.StoreCategory;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
@SuperBuilder
@Component
@EqualsAndHashCode(callSuper = false)
public class Store extends BaseEntity {

    @Column
    private String placeCode;

    @Column
    @Enumerated(EnumType.STRING)
    private StoreCategory storeCategory;
}
