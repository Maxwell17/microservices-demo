package com.example.cityservice;

import com.example.cityservice.domain.City;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/cities")
public class CityController {

    private CityService cityService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCities() {
        return cityService.getCities();
    }

}
