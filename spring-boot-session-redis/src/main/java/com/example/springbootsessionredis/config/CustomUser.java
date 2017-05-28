package com.example.springbootsessionredis.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by yamashiro-r on 2017/05/25.
 */
public class CustomUser extends User {
    public CustomUser(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
