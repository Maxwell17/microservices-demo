package com.example.authservice;

import com.example.authservice.domain.User;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@FeignClient(value = "user-service", path = "users", fallback = UserService.HystrixClientFallback.class)
public interface UserService {

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    Mono<User> getUsers();

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    Mono<User> createUser(@RequestBody User user);

    @Component
    class HystrixClientFallback implements FallbackFactory<ResponseEntity<String>> {

        @Override
        public ResponseEntity<String> create(Throwable cause) {
            return ResponseEntity.badRequest().body(cause.getMessage());
        }
    }

}
