package com.sakesage.map.domain.store.controller;

import com.sakesage.map.domain.store.dto.StoreResponse;
import com.sakesage.map.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/store")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class StoreOpenApiController {

    private final StoreService storeService;

    // 전체 조회
    @GetMapping("/")
    public ResponseEntity<List<StoreResponse>> getAllStores() {

        List<StoreResponse> storeResponseList = storeService.getAllStores();
        return ResponseEntity.ok(storeResponseList); // 응답 반환

    }
}
