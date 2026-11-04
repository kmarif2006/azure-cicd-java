package com.example.cicd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @GetMapping("/")
    public String home() {
        // return "Hello from Azure CI/CD! Version: 1.0";
        return "Hello from Azure CI/CD! Version: 3.0";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}