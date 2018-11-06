package com.example.springboot.mybatis.multi.datasource;

import com.example.springboot.mybatis.multi.datasource.mapper.apple.AppleMapper;
import com.example.springboot.mybatis.multi.datasource.mapper.pineapple.PineappleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AppleMapper appleMapper;
    @Autowired
    private PineappleMapper pineappleMapper;

    @Bean
    public CommandLineRunner appleDataSourceValuesCommandLineRunner() {
        return args -> log.info("Apple DataSource values {}", this.appleMapper.findAll());
    }

    @Bean
    public CommandLineRunner pineappleDataSourceCommandLineRunner() {
        return args -> log.info("Pineapple DataSource values {}", this.pineappleMapper.findAll());
    }

}
