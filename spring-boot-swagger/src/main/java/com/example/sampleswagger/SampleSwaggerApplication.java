package com.example.sampleswagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SampleSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSwaggerApplication.class, args);
    }
}
