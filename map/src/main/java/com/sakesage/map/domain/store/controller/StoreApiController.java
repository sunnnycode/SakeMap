package com.sakesage.map.domain.store.controller;


import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.dto.StoreResponse;
import com.sakesage.map.domain.store.dto.StoreUpdateRequest;
import com.sakesage.map.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
@CrossOrigin(origins="http://localhost:3000", allowedHeaders="*")
public class StoreApiController {


    private final StoreService storeService;

    // 가게 등록
    @PostMapping("/register")
    public ResponseEntity<StoreResponse> storeRegister (
            @RequestBody StoreRequest storeRequest) {
        StoreResponse storeResponse = storeService.storeRegister(storeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponse);
    }

    // 가게 수정
    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponse> storeUpdate(
            @PathVariable("storeId") int id,
            @RequestBody StoreUpdateRequest storeUpdateRequest
            ) {
        StoreResponse storeResponse = storeService.storeUpdate(id, storeUpdateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponse);
    }

    // 가게 삭제
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> storeDelete (
            @PathVariable("storeId") int id
    ) {
        storeService.storeDelete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("success");
    }


}
