package com.example.springbootsessionredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by yamashiro-r on 2017/05/15.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.authorizeRequests()
                .antMatchers("/session/**", "/login/*", "/").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
