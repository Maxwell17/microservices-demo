package com.example.loadbalancer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Log4j2
@Service
public record CityService(WebClient.Builder webClient) {

    private static final String CITY_SERVICE = "http://database-sync/cities";

    public Flux<?> getCities() {
        return webClient.build().get().uri(CITY_SERVICE).retrieve().bodyToFlux(Object.class);
    }

}
