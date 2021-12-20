package com.example.authservice;

import com.example.authservice.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.function.Function;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Log4j2
@Component
@AllArgsConstructor
public class UserHandler {

    public Mono<ServerResponse> creteUser(final ServerRequest request) {
        return request
                .bodyToMono(UserDTO.class)
                .map(dto -> UserDTO.builder()
                        .username(dto.getUsername())
                        .password(dto.getPassword())
                        .roles(dto.roles())
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(false)
                        .build()
                )
//                .log(null, Level.INFO)
                .flatMap((Function<Account, Mono<Account>>) acc -> this.service.save(acc))
                .flatMap((Function<Account, Mono<ServerResponse>>) acc -> created(URI.create(""))
                        .contentType(APPLICATION_JSON)
                        .bodyValue(acc)
                ).onErrorResume(th -> ServerResponse.badRequest()
                        .contentType(APPLICATION_JSON)
                        .bodyValue(th.getMessage())
                );
    }

}
