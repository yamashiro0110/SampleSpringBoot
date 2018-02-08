package com.example.sampleswagger.rest.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("サンプルAPI")
@RestController
@RequestMapping(path = "/api/sample", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SampleApiController {

    @ModelAttribute
    SampleApiRequest bindParameter(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("key_word") String kw) {
        return new SampleApiRequest(page, size, kw);
    }

    @ApiOperation("sample get api")
    @GetMapping
    public SampleApiResponse get(@ModelAttribute @Valid SampleApiRequest request) {
        return new SampleApiResponse(30, 35, "get", "sample", request.getKeyWord());
    }

    @ApiOperation("sample post api")
    @PostMapping
    public SampleApiResponse post(@RequestBody @Valid SampleApiRequest request) {
        return new SampleApiResponse(30, 35, "post", "sample", request.getKeyWord());
    }

}
