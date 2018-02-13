package com.example.sampleswagger.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/400")
    @ResponseBody
    public ResponseEntity<ErrorResponse> badRequest() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @RequestMapping("/401")
    @ResponseBody
    public ResponseEntity<ErrorResponse> unauthorized() {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @RequestMapping("/403")
    @ResponseBody
    public ResponseEntity<ErrorResponse> firbidden() {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @RequestMapping("/500")
    @ResponseBody
    public ResponseEntity<ErrorResponse> serverError() {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @RequestMapping("/503")
    @ResponseBody
    public ResponseEntity<ErrorResponse> serviceUnavailable() {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        ErrorResponse errorResponse = new ErrorResponse(httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

}
