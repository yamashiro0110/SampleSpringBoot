package com.example.springbootsessionredis.controller.login;

import com.example.springbootsessionredis.config.security.custom.CustomLoginFormSecurityConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@ConditionalOnBean(CustomLoginFormSecurityConfig.class)
@Controller
@RequestMapping("/login/custom")
public class CustomLoginController {
    @GetMapping
    String customLoginForm() {
        return "custom_login";
    }

}
