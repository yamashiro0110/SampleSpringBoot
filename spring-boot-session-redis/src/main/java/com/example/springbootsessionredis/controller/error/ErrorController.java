package com.example.springbootsessionredis.controller.error;

import com.example.springbootsessionredis.filter.MaintenanceModeFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/error")
@ConditionalOnMissingBean(MaintenanceModeFilter.class)
public class ErrorController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @RequestMapping(path = "404", produces = MediaType.TEXT_HTML_VALUE)
    String error404() {
        return "error/404";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @RequestMapping(path = "500", produces = MediaType.TEXT_HTML_VALUE)
    String error500() {
        return "error/500";
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @RequestMapping(path = "503", produces = MediaType.TEXT_HTML_VALUE)
    String error503() {
        return "error/503";
    }

    @RequestMapping(path = "404", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> apiError404() {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(this.errorMap(httpStatus), httpStatus);
    }

    @RequestMapping(path = "500", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> apiError500() {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(this.errorMap(httpStatus), httpStatus);
    }

    @RequestMapping(path = "503", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> apiError503() {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        return new ResponseEntity<>(this.errorMap(httpStatus), httpStatus);
    }

    Map<String, Object> errorMap(HttpStatus httpStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", httpStatus.value());
        map.put("message", httpStatus.getReasonPhrase());
        return map;
    }
}
