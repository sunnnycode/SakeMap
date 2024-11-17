package com.sakesage.map.domain.api.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/adminHome")
    public String adminHomePage() {
        return "adminHome";
    }
}
