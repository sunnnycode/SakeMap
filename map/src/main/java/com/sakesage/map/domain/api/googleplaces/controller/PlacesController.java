package com.sakesage.map.domain.api.googleplaces.controller;

import com.sakesage.map.domain.api.googleplaces.service.GooglePlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlacesController {

    @Autowired
    private GooglePlacesService googlePlacesService;

    @GetMapping("/place-details")
    public String getPlaceDetails(@RequestParam String placeCode) {
        return googlePlacesService.getPlaceDetails(placeCode);
    }
}
