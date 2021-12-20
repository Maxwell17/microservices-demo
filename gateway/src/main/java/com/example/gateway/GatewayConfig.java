//package com.example.gateway;
//
//import lombok.AllArgsConstructor;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@AllArgsConstructor
//public class GatewayConfig {
//
//    private JwtAuthenticationFilter filter;
//
//    @Bean
//    public RouteLocator routes(RouteLocatorBuilder builder) {
//        return builder.routes().route("auth", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("lb://user-service")).build();
//    }
//
//}