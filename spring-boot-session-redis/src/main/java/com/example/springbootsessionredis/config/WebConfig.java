package com.example.springbootsessionredis.config;

import com.example.springbootsessionredis.view.CustomLinkBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * Created by yamashiro-r on 2017/06/17.
 */
@Configuration
@Import(ThymeleafAutoConfiguration.class)
public class WebConfig {
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private CustomLinkBuilder customLinkBuilder;

    @Bean
    public SpringTemplateEngine customTemplateEngine() {
        this.templateEngine.setLinkBuilder(this.customLinkBuilder);
        return this.templateEngine;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
