package com.example.springbootsessionredis.config.security.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yamashiro-r on 2017/08/08.
 */
@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseStatus
    @ExceptionHandler(IllegalStateException.class)
    public String handleIllegalState(Exception e) {
        // TODO: exception handling
        LOGGER.error("エラーが発生しました", e);
        return "error/50x";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({IllegalArgumentException.class})
    public String notFound(Exception e) {
        // TODO: exception handling
        LOGGER.error("ページが見つかりません", e);
        return "error/404";
    }

}
