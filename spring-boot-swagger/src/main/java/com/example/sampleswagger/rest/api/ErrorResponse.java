package com.example.sampleswagger.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    @JsonProperty
    private String title;
    @JsonProperty
    private String detail;

    public ErrorResponse(HttpStatus httpStatus) {
        this.title = httpStatus.name();
        this.detail = httpStatus.getReasonPhrase();
    }
}
