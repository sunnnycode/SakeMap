package com.sakesage.map.domain.api.googleplaces.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GooglePlacesService {

    private final String API_KEY = "AIzaSyCkAAj6kvP-nK9QK8-SmP-sKIOiL49R0Jo"; // 발급받은 API 키
    private final String BASE_URL = "https://maps.googleapis.com/maps/api/place/details/json";

    @Autowired
    private RestTemplate restTemplate;

    public String getPlaceDetails(String placeId) {
        String url = String.format("%s?place_id=%s&key=%s", BASE_URL, placeId, API_KEY);
        return restTemplate.getForObject(url, String.class);
    }
}
