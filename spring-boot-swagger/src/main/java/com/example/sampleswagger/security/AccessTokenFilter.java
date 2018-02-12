package com.example.sampleswagger.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/sample/auth"})
public class AccessTokenFilter extends OncePerRequestFilter {
    private static final String AUTH_HEADER = "Authorization";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader(AUTH_HEADER);
        this.logger.debug("auth header:{}", accessToken);

        if (!StringUtils.hasText(accessToken)) {
            this.logger.warn("auth header not contained");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "error");
            return;
        }

        if (!"testAccessToken".equals(accessToken)) {
            this.logger.warn("invalid token {}", accessToken);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "error");
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(this.authentication(accessToken));
        filterChain.doFilter(request, response);
        SecurityContextHolder.clearContext();
    }

    private Authentication authentication(String accessToken) {
        LoginUser loginUser = new LoginUser(accessToken);
        return new AccessTokenAuthentication(loginUser);
    }
}
