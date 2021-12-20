package com.example.loadbalancer;

import com.example.loadbalancer.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public record UserService(WebClient.Builder webClient) {

    private static final String USER_SERVICE = "http://user-service/users";

    public Flux<?> getUsers() {
        return webClient.build()
                .get()
                .uri(USER_SERVICE)
                .retrieve()
                .bodyToFlux(Object.class);
    }

    public Mono<?> createUser(Mono<User> user) {
        return webClient.build()
                .post()
                .uri(USER_SERVICE)
                .body(user, User.class)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<?> updateUser(Mono<User> user) {
        return webClient.build()
                .put()
                .uri(USER_SERVICE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(user, User.class)
                .retrieve()
                .bodyToMono(Object.class);
    }

}
