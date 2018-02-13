package com.example.sampleswagger.rest.api;

import com.example.sampleswagger.security.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@Api("サンプルAPI")
@RestController
@RequestMapping(path = "/api/sample", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SampleApiController {

    @ApiOperation("sample get public api")
    @GetMapping("public")
    public SampleApiResponse getPublic(@ModelAttribute @Valid SampleApiRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String detail = "has authentication: " + Objects.nonNull(authentication);
        return new SampleApiResponse(30, 35, "get", "sample public api", request.getKeyWord(), detail);
    }

    @ApiOperation("sample post public api")
    @PostMapping("public")
    public SampleApiResponse postPublic(@RequestBody @Valid SampleApiRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String detail = "has authentication: " + Objects.nonNull(authentication);
        return new SampleApiResponse(30, 35, "post", "sample public api", request.getKeyWord(), detail);
    }

    @ApiOperation("sample get authenticated api")
    @GetMapping("auth")
    public SampleApiResponse getAuthenticated(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "size", defaultValue = "1", required = false) int size,
            @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return new SampleApiResponse(30, 35, "get", "sample authenticated api", keyword, loginUser.toString());
    }

    @ApiOperation("sample post authenticated api")
    @PostMapping("auth")
    public SampleApiResponse postAuthenticated(@RequestBody @Valid SampleApiRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return new SampleApiResponse(30, 35, "post", "sample authenticated api", request.getKeyWord(), loginUser.toString());
    }

}
