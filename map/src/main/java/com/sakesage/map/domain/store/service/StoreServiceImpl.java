package com.sakesage.map.domain.store.service;

import com.sakesage.map.db.store.Store;
import com.sakesage.map.db.store.StoreRepository;
import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.dto.StoreResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public StoreResponse storeRegister(StoreRequest storeRequest){

        Store store = modelMapper.map(storeRequest, Store.class);

        store.setId(store.getId());

        Store savedStore = storeRepository.save(store);
        return modelMapper.map(savedStore, StoreResponse.class);

    }

}
