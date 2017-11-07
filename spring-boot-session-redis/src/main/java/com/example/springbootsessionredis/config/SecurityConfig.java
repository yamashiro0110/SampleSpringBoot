package com.example.springbootsessionredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import javax.annotation.Resource;

/**
 * Created by yamashiro-r on 2017/05/15.
 */
@Configuration
@Order
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;

    private String[] publicUrls() {
        return new String[]{
                "/session/**",
                "/login/**",
                "/health/**",
                "/exception/**",
                "/info",
                "/metrics",
                "/trace",
                "/auto/login",
                "/h2-console/**",
                "/image/**",
                "/",
        };
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(this.publicUrls())
                .permitAll()
                .anyRequest()
                .authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user")
                .usernameParameter("username")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.csrf()
                .ignoringAntMatchers("/h2-console/**")
                .disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionAuthenticationErrorUrl("/login")
                .invalidSessionUrl("/session");

        http.rememberMe()
                .rememberMeServices(this.springSessionRememberMeServices())
                .userDetailsService(this.userDetailsService);

        http.httpBasic().disable();
    }

    @Bean
    SpringSessionRememberMeServices springSessionRememberMeServices() {
        SpringSessionRememberMeServices springSessionRememberMeServices = new SpringSessionRememberMeServices();
        springSessionRememberMeServices.setAlwaysRemember(true);
        springSessionRememberMeServices.setValiditySeconds(30);
        return springSessionRememberMeServices;
    }

}
