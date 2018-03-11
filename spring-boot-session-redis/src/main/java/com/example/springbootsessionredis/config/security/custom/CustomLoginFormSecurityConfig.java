package com.example.springbootsessionredis.config.security.custom;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ConditionalOnProperty(prefix = "security", name = "multipleLogin", havingValue = "true")
@Order(1)
public class CustomLoginFormSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.mvcMatcher("/user/custom/**")
                // authorizeRequests
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                // formLogin
                .formLogin()
                .loginPage("/login/custom")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .defaultSuccessUrl("/user/custom")
                .permitAll()
                .and()
                // logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login/custom")
                .permitAll()
                .and()
                // sessionManagement
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionAuthenticationErrorUrl("/login/custom")
                .invalidSessionUrl("/session")
                .and()
                // httpBasic
                .httpBasic()
                .disable();
    }
}
