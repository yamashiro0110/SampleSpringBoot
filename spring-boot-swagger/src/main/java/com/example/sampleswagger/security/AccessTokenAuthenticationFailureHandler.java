package com.example.sampleswagger.security;

import com.example.sampleswagger.rest.api.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AccessTokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    @Override
    public void onAuthenticationFailure(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final AuthenticationException exception) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse("authentication error", exception.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        try(PrintWriter writer = response.getWriter()) {
            String responseBody = this.objectMapper.writeValueAsString(errorResponse);
            writer.write(responseBody);
        }
    }
}
