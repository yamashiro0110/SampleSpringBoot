package com.example.sampleswagger.rest.api;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("サンプルAPI")
@RestController("api/sample")
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SampleApiController {

    @GetMapping
    public SampleApiResponse get(@ModelAttribute @Valid SampleApiRequest request) {
        return new SampleApiResponse(30, 35, "get", "sample");
    }

    @PostMapping
    public SampleApiResponse post(@RequestBody @Valid SampleApiRequest request) {
        return new SampleApiResponse(30, 35, "post", "sample");
    }

}
