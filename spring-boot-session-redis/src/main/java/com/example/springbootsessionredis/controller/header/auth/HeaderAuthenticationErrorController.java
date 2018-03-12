package com.example.springbootsessionredis.controller.header.auth;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/error/header/authentication")
public class HeaderAuthenticationErrorController {
    @GetMapping("session/invalid")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String invalidSession(Model model) {
        model.addAttribute("msg", "invalidSession");
        return "header_authentication/error";
    }

    @GetMapping("session/authentication")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String sessionAuthenticationError(Model model) {
        model.addAttribute("msg", "sessionAuthenticationError");
        return "header_authentication/error";
    }

    @GetMapping("access_denied")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String accessDeniedError(Model model) {
        model.addAttribute("msg", "accessDenied");
        return "header_authentication/error";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String authenticationError(Model model) {
        model.addAttribute("msg", "authorization header authentication Error");
        return "header_authentication/error";
    }

}
