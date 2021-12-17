package com.example.cityservice;

import com.example.cityservice.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user) {
        return userService.updateUser(user);
    }

}
