package com.example.sampleswagger.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    @JsonProperty
    private String title;
    @JsonProperty
    private String detail;
}
