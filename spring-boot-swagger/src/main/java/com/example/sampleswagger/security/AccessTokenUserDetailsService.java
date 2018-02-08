package com.example.sampleswagger.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
    @Override
    public UserDetails loadUserDetails(final PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        String accessToken = (String) token.getPrincipal();

        if (!"testAccessToken".equals(accessToken)) {
            throw new UsernameNotFoundException("invalid access token:" + accessToken);
        }

        return new User(accessToken, "", AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
