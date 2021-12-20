package com.example.loadbalancer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    private Long id;

    private String name;

    private Integer population;
}
