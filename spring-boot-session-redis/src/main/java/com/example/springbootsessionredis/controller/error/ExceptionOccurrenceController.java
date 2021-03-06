package com.example.springbootsessionredis.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yamashiro-r on 2017/08/08.
 */
@Controller
@RequestMapping("/exception/occurrence")
public class ExceptionOccurrenceController {

    @GetMapping
    String error() {
        throw new IllegalStateException("エラーを発生させます");
    }

    @GetMapping("page")
    String errorPage() {
        return "exception";
    }
}
