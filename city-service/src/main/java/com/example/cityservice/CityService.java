package com.example.cityservice;

import com.example.cityservice.domain.City;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@FeignClient(value = "database-sync", path = "cities", fallback = CityService.HystrixClientFallback.class)
public interface CityService {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getCities();

    class HystrixClientFallback implements FallbackFactory<ResponseEntity<String>> {

        @Override
        public ResponseEntity<String> create(Throwable cause) {
            return ResponseEntity.badRequest().body(cause.getMessage());
        }
    }
}
