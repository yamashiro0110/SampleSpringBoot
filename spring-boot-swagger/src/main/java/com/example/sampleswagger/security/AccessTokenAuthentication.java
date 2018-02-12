package com.example.sampleswagger.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Objects;

public class AccessTokenAuthentication implements Authentication {
    private final LoginUser loginUser;

    public AccessTokenAuthentication(final LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_LOGIN_USER");
    }

    @Override
    public Object getCredentials() {
        return this.loginUser.getToken();
    }

    @Override
    public Object getDetails() {
        return this.loginUser.toString();
    }

    @Override
    public Object getPrincipal() {
        return this.loginUser;
    }

    @Override
    public boolean isAuthenticated() {
        return Objects.nonNull(this.loginUser);
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
        // not support operation
    }

    @Override
    public String getName() {
        return this.loginUser.getId();
    }
}
