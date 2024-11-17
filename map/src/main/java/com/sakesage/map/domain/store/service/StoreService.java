package com.sakesage.map.domain.store.service;

import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.dto.StoreResponse;

public interface StoreService {

    StoreResponse storeRegister(StoreRequest storeRequest);
}
