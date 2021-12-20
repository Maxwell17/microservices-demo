package com.example.loadbalancer;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Component
public class CityRouter {

    @Bean
    public RouterFunction<?> getCities(final CityHandler handler) {
        return RouterFunctions
                .route(GET("/cities"), handler::getCities);

    }

}
