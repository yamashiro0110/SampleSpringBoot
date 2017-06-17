package com.example.springbootsessionredis.config;

import com.example.springbootsessionredis.model.user.User;
import com.example.springbootsessionredis.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by yamashiro-r on 2017/05/25.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByName(username);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("invalid username");
        }

        return new CustomUser(username, user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
