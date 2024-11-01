package com.sakesage.map.domain.store.controller;


import com.sakesage.map.common.api.Api;
import com.sakesage.map.domain.store.dto.StoreRequest;
import com.sakesage.map.domain.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
@CrossOrigin(origins="http://localhost:3000", allowedHeaders="*")
public class StoreApiController {


    private final StoreService storeService;

    @PostMapping("/add")
    public Api<String> storeAdd (
            @RequestBody StoreRequest storeRequest) throws Exception {

        storeService.storeAdd(storeRequest);
        return Api.OK("Store added successfully");
    }
}
