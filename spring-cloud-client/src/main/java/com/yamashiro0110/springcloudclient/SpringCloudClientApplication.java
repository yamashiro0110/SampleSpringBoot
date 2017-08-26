package com.yamashiro0110.springcloudclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringCloudClientApplication {
    @Value("${sample.spring.cloud.message}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudClientApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "message is:" + this.message;
    }

}
