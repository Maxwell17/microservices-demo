package com.example.loadbalancer;

import com.example.loadbalancer.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Log4j2
@Component
@AllArgsConstructor
public class UserHandler {

    private UserService userService;

    public Mono<ServerResponse> getUsers(final ServerRequest request) {
        return ok()
                .contentType(APPLICATION_JSON)
                .body(this.userService.getUsers(), Object.class);
    }

    public Mono<ServerResponse> createUser(final ServerRequest request) {
        return ok()
                .contentType(APPLICATION_JSON)
                .body(this.userService.createUser(request.bodyToMono(User.class)), Object.class);
    }

    public Mono<ServerResponse> updateUser(final ServerRequest request) {
        return ok()
                .contentType(APPLICATION_JSON)
                .body(this.userService.updateUser(request.bodyToMono(User.class)), Object.class);
    }
}
