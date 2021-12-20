package com.example.cityservice;

import com.example.cityservice.domain.User;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@FeignClient(value = "user-service", path = "users", fallback = UserService.HystrixClientFallback.class)
public interface UserService {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getUsers();

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUser(@RequestBody User user);

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> updateUser(@RequestBody User user);

    class HystrixClientFallback implements FallbackFactory<ResponseEntity<String>> {

        @Override
        public ResponseEntity<String> create(Throwable cause) {
            return ResponseEntity.badRequest().body(cause.getMessage());
        }
    }
}
