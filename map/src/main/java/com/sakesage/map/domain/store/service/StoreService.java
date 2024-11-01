package com.sakesage.map.domain.store.service;

import com.sakesage.map.domain.store.dto.StoreRequest;

public interface StoreService {

    void storeAdd(StoreRequest storeRequest) throws Exception;
}
