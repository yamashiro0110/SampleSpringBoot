package com.example.springbootsessionredis.config.security.header.auth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@ConditionalOnProperty(prefix = "security", name = "headerAuthentication", havingValue = "true")
@Order(1)
public class HeaderAuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

    AuthenticationFailureHandler authenticationFailureHandler() {
        SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
        authenticationFailureHandler.setDefaultFailureUrl("/error/header/authentication");
        authenticationFailureHandler.setUseForward(true);
        return authenticationFailureHandler;
    }

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
        // 認証失敗時のhandler
//        filter.setAuthenticationFailureHandler(this.authenticationFailureHandler());
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
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/header/authentication/access_denied")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionAuthenticationErrorUrl("/error/header/authentication/session/authentication")
                .invalidSessionUrl("/error/header/authentication/session/invalid")
                .and()
                .csrf()
                .and()
                .addFilter(this.preAuthenticationFilter())
        ;
    }
}
