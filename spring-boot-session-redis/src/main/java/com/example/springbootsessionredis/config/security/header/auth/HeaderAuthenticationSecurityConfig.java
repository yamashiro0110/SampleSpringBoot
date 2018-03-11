package com.example.springbootsessionredis.config.security.header.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@Order(1)
public class HeaderAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * RequestHeaderからAuthorizationヘッダを取り出して認証を行うFilter
     */
    @Bean
    RequestHeaderAuthenticationFilter preAuthenticationFilter() throws Exception {
        RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
        // Authorizationヘッダから認証情報を取得する
        filter.setPrincipalRequestHeader("Authorization");
        // Authorizationヘッダの値が変わったときに認証を行うためtrue
        filter.setCheckForPrincipalChanges(true);
        // RequestHeaderにAuthorizationヘッダが指定されていないと'/header/authentication'以外のパスでも認証エラーになるためfalse
        filter.setExceptionIfHeaderMissing(false);
        // 認証処理を行う
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

    /**
     * Authorizationヘッダの値を元に認証を行う
     * <p>
     * AuthenticationManagerから委譲される
     */
    @Bean
    PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authenticationProvider = new PreAuthenticatedAuthenticationProvider();
        authenticationProvider.setPreAuthenticatedUserDetailsService(this.headerAuthenticationUserDetailsService());
        return authenticationProvider;
    }

    /**
     * Authorizationヘッダの値からユーザー情報を取得して認証情報を返す
     * <p>
     * preAuthenticatedAuthenticationProviderから呼ばれる
     */
    @Bean
    AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> headerAuthenticationUserDetailsService() {
        return new HeaderAuthenticationUserDetailsService();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        // authenticationProviderをセット
        auth.authenticationProvider(this.preAuthenticatedAuthenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.mvcMatcher("/header/authentication")
                .addFilter(this.preAuthenticationFilter())
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionAuthenticationErrorUrl("/")
                .and()
                .csrf()
        ;
    }
}
