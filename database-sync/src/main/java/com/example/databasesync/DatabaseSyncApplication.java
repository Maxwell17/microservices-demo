package com.example.databasesync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DatabaseSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseSyncApplication.class, args);
    }

}
