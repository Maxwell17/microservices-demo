package com.example.authservice;

import com.example.authservice.domain.Role;
import com.example.authservice.domain.User;
import com.example.authservice.domain.UserDTO;
import com.example.authservice.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.created;

@Log4j2
@Component
@AllArgsConstructor
public class UserHandler {

    private UserService userService;

    @NotNull
    public Mono<ServerResponse> creteUser(final ServerRequest request) {
        return request
                .bodyToMono(UserDTO.class)
                .map(dto -> User.builder()
                        .email(dto.getUsername())
                        .password(dto.getPassword())
                        .roles(List.of(Role.builder().role(UserRole.USER_ROLE).build()))
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(false)
                        .build()
                )
                .log(null, Level.INFO)
                .publishOn(Schedulers.boundedElastic())
                .flatMap(new Function<User, Mono<User>>() {
                    @Override
                    public Mono<User> apply(User user) {
                        return userService.createUser(user);
                    }
                }).flatMap(new Function<User, Mono<ServerResponse>>() {
                    @Override
                    public Mono<ServerResponse> apply(User user) {
                        return created(URI.create("")).contentType(APPLICATION_JSON).body(user, User.class);
                    }
                }).onErrorResume(new Function<Throwable, Mono<ServerResponse>>() {
                    @Override
                    public Mono<ServerResponse> apply(Throwable th) {
                        return ServerResponse.badRequest()
                                .contentType(APPLICATION_JSON)
                                .bodyValue(th.getMessage());
                    }
                });
    }

}
