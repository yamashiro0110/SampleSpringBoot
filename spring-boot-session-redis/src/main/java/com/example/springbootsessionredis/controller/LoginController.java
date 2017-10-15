package com.example.springbootsessionredis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yamashiro-r on 2017/06/17.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    String login() {
        return "login";
    }

    @GetMapping("custom")
    String customLoginForm() {
        return "custom_login";
    }

}
