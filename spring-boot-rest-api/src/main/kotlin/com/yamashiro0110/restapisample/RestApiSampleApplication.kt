package com.yamashiro0110.restapisample

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class RestApiSampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(RestApiSampleApplication::class.java, *args)
}

@Configuration
class SampleApiConfig {

    @Bean
    fun objectMapper() = ObjectMapper().registerModule(Jdk8Module())

}
