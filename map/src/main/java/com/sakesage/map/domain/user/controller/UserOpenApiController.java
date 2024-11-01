package com.sakesage.map.domain.user.controller;

import com.sakesage.map.common.api.Api;
import com.sakesage.map.domain.token.controller.model.TokenResponse;
import com.sakesage.map.domain.user.business.UserBusiness;
import com.sakesage.map.domain.user.dto.UserLoginRequest;
import com.sakesage.map.domain.user.dto.UserRegisterRequest;
import com.sakesage.map.domain.user.dto.UserResponse;
import com.sakesage.map.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
@CrossOrigin(origins="http://localhost:3000", allowedHeaders="*")
public class UserOpenApiController {

    private final UserService userService;
    private final UserBusiness userBusiness;

    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        var response = userBusiness.register(request);  // response가 UserResponse 타입이어야 함
        return Api.OK(response);
    }


    @PostMapping("/login")
    public Api<TokenResponse> login(
            @Valid @RequestBody UserLoginRequest request
    ){
        var response = userBusiness.login(request);
        return Api.OK(response);
    }

}
