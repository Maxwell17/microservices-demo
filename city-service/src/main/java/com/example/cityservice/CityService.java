package com.example.cityservice;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "database-sync", fallback = CityService.HystrixClientFallback.class)
public interface CityService {

    @GetMapping("/cities")
    ResponseEntity<?> cities();

    class HystrixClientFallback implements FallbackFactory<ResponseEntity<String>> {

        @Override
        public ResponseEntity<String> create(Throwable cause) {
            return ResponseEntity.badRequest().body(cause.getMessage());
        }
    }
}
