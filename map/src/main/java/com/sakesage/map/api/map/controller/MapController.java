package com.sakesage.map.api.map.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {

    @GetMapping("/")
    public String main() {
        return "main"; // Ensure this returns "main" to match "main.html"
    }
}
