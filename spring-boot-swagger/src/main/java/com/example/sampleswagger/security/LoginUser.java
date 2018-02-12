package com.example.sampleswagger.security;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@ToString
public class LoginUser {
    private LocalDateTime lastAccessTime = LocalDateTime.now();
    private String id;
    private String token;

    public LoginUser(final String token) {
        this.token = token;
        this.id = UUID.randomUUID().toString();
    }
}
