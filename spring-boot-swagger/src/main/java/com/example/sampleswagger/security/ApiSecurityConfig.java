package com.example.sampleswagger.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private AccessTokenUserDetailsService accessTokenUserDetailsService;
    @Value("${api.auth.urls:/api/**}")
    private List<String> authenticatedUrlPatterns;

    @Bean
    RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() {
        try {
            RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
            filter.setPrincipalRequestHeader("Authorization");
            filter.setAuthenticationManager(authenticationManager());
            filter.setAuthenticationFailureHandler(new AccessTokenAuthenticationFailureHandler());
//            filter.setExceptionIfHeaderMissing(false);
            return filter;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    PreAuthenticatedAuthenticationProvider authenticationProvider() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(this.accessTokenUserDetailsService);
        return authenticationProvider;
    }

    @Bean
    FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setUrlPatterns(this.authenticatedUrlPatterns);
        filterRegistrationBean.setFilter(this.requestHeaderAuthenticationFilter());
        return filterRegistrationBean;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.mvcMatcher("/api/**").addFilter(this.requestHeaderAuthenticationFilter());
    }
}
