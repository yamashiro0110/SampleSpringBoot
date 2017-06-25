package com.example.springbootsessionredis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yamashiro-r on 2017/06/25.
 */
@Controller
@RequestMapping("/health")
public class HealthCheckController {

    @RequestMapping("index.html")
    String result() {
        return "forward:/health";
    }

}
