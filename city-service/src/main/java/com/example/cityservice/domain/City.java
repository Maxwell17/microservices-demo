package com.example.cityservice.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class City implements Serializable {

    Long id;

    String name;

    Integer population;

    public City() {

    }
}
