package com.sakesage.map.db.store;

import com.sakesage.map.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private String placeId;
}
