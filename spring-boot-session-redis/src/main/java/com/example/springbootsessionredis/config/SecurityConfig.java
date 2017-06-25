package com.example.springbootsessionredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user")
                .usernameParameter("username")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.authorizeRequests()
                .antMatchers(
                        "/session/**",
                        "/login**",
                        "/health**",
                        "/info",
                        "/metrics",
                        "/trace",
                        "/auto/login",
                        "/h2-console/**",
                        "/image/**",
                        "/")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable();

        http.sessionManagement()
                .sessionAuthenticationErrorUrl("/login")
                .invalidSessionUrl("/session");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
