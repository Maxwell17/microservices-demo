package com.example.loadbalancer;

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
public class CityHandler {

    private CityService cityService;

    public Mono<ServerResponse> getCities(final ServerRequest request) {
        return ok()
                .contentType(APPLICATION_JSON)
                .body(this.cityService.getCities(), Object.class);
    }

}
