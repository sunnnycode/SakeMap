package com.sakesage.map.domain.store.dto;

import com.sakesage.map.db.store.enums.StoreCategory;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StoreResponse {

    private Integer id;

    private String placeCode;

    private StoreCategory storeCategory;
}
