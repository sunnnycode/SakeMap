package com.sakesage.map.domain.store.dto;

import com.sakesage.map.db.store.enums.StoreCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreUpdateRequest {

    @NotNull
    private String placeCode;

    @NotNull
    private StoreCategory storeCategory;
}
