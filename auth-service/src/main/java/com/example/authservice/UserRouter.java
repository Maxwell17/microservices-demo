package com.example.authservice;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;

@Component
public class UserRouter {

    @Bean
    public RouterFunction<ServerResponse> createAccount(final @NotNull UserHandler handler) {
        return RouterFunctions
                .route(POST("/auth").and(contentType(APPLICATION_JSON)), handler::creteUser);

    }

}
