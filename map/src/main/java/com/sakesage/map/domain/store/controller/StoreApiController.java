package com.sakesage.map.domain.store.controller;


import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.dto.StoreResponse;
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

//    // 가게 수정
//    // 가게 권한 수정(role == master)
//    @PutMapping("/{storeId}")
//    public ResponseEntity<StoreResponse> storeUpdate(
//            @PathVariable int storeId,
//            @RequestBody StoreRequest storeRequest
//    ) {
//        StoreResponse storeResponse = storeService.updateStore(userId, statusRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(storeResponse);
//    }

}
