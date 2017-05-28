package com.example.springbootsessionredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringBootSessionRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSessionRedisApplication.class, args);
    }
}
