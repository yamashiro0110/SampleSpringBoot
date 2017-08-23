package com.example.springbootsessionredis.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by yamashiro-r on 2017/05/25.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (!StringUtils.equals(username, "admin")) {
            throw new UsernameNotFoundException("invalid username");
        }

        String password = this.passwordEncoder.encode("hogehoge");
        return new CustomUser(username, password, AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
