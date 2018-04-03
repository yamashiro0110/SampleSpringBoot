package com.example.springbootsessionredis.config.security.header.auth;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * HttpRequestHeaderから取り出した認証情報を元に認証ユーザーを取得する
 */
public class HeaderAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserDetails(final PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        String tokenValue = String.valueOf(token.getPrincipal());
        this.logger.debug("loadUserDetails開始 {}", token);

        if (!StringUtils.startsWith(tokenValue, "hogehoge")) {
            this.logger.debug("loadUserDetails失敗 {}", token);
            throw new BadCredentialsException("invalid authorization token");
        }

        this.logger.debug("loadUserDetails成功 {}", token);

        return User.withUsername("headerAuthenticatedUser:" + tokenValue)
                .password(tokenValue)
                .authorities("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
