package com.example.springbootsessionredis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by yamashiro-r on 2017/05/25.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (!Collections.singletonList("admin").contains(username)) {
            throw new UsernameNotFoundException("invalid username");
        }

        String pw = this.passwordEncoder.encode("admin");
        return new CustomUser(username, pw, AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
