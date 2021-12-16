package com.example.cityservice;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CityServiceImpl {

    private CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<?> cities() {
        return cityService.cities();
    }

}
