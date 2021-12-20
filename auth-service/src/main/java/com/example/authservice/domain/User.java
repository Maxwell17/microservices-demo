package com.example.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements  {

    private Long id;

    private String username;

    private String password;

}
