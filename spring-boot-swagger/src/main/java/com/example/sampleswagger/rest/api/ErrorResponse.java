package com.example.sampleswagger.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    @JsonProperty
    private Map<String, Object> error;

    public ErrorResponse(HttpStatus httpStatus) {
        this.error = new HashMap<>();
        this.error.put("code", httpStatus.value());
        this.error.put("detail", httpStatus.getReasonPhrase());
    }
}
