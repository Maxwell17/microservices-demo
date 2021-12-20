package com.example.loadbalancer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Component
public class UserRouter {

    @Bean
    public RouterFunction<?> getUsers(final UserHandler handler) {
        return RouterFunctions
                .route(GET("/users"), handler::getUsers);
    }

    @Bean
    public RouterFunction<?> creteUser(final UserHandler handler) {
        return RouterFunctions
                .route(POST("/users"), handler::createUser);
    }

    @Bean
    public RouterFunction<?> updateUser(final UserHandler handler) {
        return RouterFunctions
                .route(PUT("/users"), handler::getUsers);
    }

}
