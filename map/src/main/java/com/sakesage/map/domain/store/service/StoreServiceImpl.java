package com.sakesage.map.domain.store.service;

import com.sakesage.map.db.store.Store;
import com.sakesage.map.db.store.StoreRepository;
import com.sakesage.map.domain.store.dto.StoreRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public void storeAdd(StoreRequest storeRequest) throws Exception {

        // Store 엔티티 생성 및 저장
        Store store = Store.builder()
                .placeId(storeRequest.getPlaceId())
                .build();

        storeRepository.save(store);
    }

}
