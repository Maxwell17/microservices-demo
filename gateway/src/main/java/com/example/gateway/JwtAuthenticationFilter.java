package com.example.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        final List<String> apiEndpoints = List.of("/register", "/login", "/auth");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);

                return response.setComplete();
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            try {
                jwtUtil.validateToken(token);
            } catch (SignatureException | IllegalArgumentException e) {
                // e.printStackTrace();

                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);

                return response.setComplete();
            }

            Claims claims = jwtUtil.getClaims(token);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
        }

        return chain.filter(exchange);
    }

}
