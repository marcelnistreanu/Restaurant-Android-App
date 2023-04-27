package com.example.springbootbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBackendApplication.class, args);
    }

}
