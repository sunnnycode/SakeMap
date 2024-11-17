package com.sakesage.map.domain.store.service;

import com.sakesage.map.db.store.Store;
import com.sakesage.map.db.store.StoreRepository;
import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.dto.StoreResponse;
import com.sakesage.map.domain.store.dto.StoreUpdateRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // 가게 등록
    @Override
    @Transactional
    public StoreResponse storeRegister(StoreRequest storeRequest){
        Store store = modelMapper.map(storeRequest, Store.class);

        store.setId(store.getId());

        Store savedStore = storeRepository.save(store);
        return modelMapper.map(savedStore, StoreResponse.class);
    }

    // 가게 수정
    @Override
    public StoreResponse storeUpdate(int storeId, StoreUpdateRequest storeUpdateRequest) {

        // 요청에서 받은 storeId를 이용해 가게 정보 조회
        Optional<Store> optionalStore = storeRepository.findById(storeId);

        if (!optionalStore.isPresent()) {
            throw new RuntimeException("Store not found with id " + storeId);
        }

        // 기존 가게 정보 가져오기
        Store store = optionalStore.get();
        modelMapper.map(storeUpdateRequest, store);
        Store updatedStore = storeRepository.save(store);
        StoreResponse storeResponse = modelMapper.map(updatedStore, StoreResponse.class);

        return storeResponse;
    }

    // 가게 삭제
    @Override
    public void storeDelete(int storeId) {

        // 요청에서 받은 storeId를 이용해 가게 정보 조회
        Optional<Store> optionalStore = storeRepository.findById(storeId);

        if (!optionalStore.isPresent()) {
            throw new RuntimeException("Store not found with id " + storeId);
        }

        Store store = optionalStore.get();
        storeRepository.delete(store);
    }

    // 가게 전체 조회
    @Override
    public List<StoreResponse> getAllStores() {
        return storeRepository.findAll().stream()
                .map(store -> new StoreResponse(
                        store.getId(),
                        store.getPlaceCode(),
                        store.getStoreCategory()))
                .collect(Collectors.toList());
    }

}
