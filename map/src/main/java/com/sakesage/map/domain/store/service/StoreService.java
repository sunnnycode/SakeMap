package com.sakesage.map.domain.store.service;

import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.dto.StoreResponse;
import com.sakesage.map.domain.store.dto.StoreUpdateRequest;

import java.util.List;

public interface StoreService {

    StoreResponse storeRegister(StoreRequest storeRequest);
    StoreResponse storeUpdate(int id, StoreUpdateRequest storeUpdateRequest);
    void storeDelete(int id);
    List<StoreResponse> getAllStores();

}
