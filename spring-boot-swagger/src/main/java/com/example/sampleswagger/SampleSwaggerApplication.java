package com.example.sampleswagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@ServletComponentScan
public class SampleSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleSwaggerApplication.class, args);
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public WebApplicationInitializer webApplicationInitializer() {
        return servletContext -> {
            this.logger.debug("start webApp");
        };
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return registry -> {
            registry.addErrorPages(
                    new ErrorPage(HttpStatus.BAD_REQUEST, "/400"),
                    new ErrorPage(HttpStatus.UNAUTHORIZED, "/401"),
                    new ErrorPage(HttpStatus.FORBIDDEN, "/403"),
                    new ErrorPage(HttpStatus.NOT_FOUND, "/404"),
                    new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"),
                    new ErrorPage(HttpStatus.SERVICE_UNAVAILABLE, "/503")
            );
        };
    }
}
