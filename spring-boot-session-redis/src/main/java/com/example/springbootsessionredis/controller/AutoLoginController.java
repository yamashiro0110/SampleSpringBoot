package com.example.springbootsessionredis.controller;

import com.example.springbootsessionredis.config.CustomUser;
import com.example.springbootsessionredis.config.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by yamashiro-r on 2017/06/01.
 */
@Controller
@RequestMapping("/auto/login")
public class AutoLoginController {
    @Resource
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping
    String login() {
        CustomUser userDetails = (CustomUser) this.customUserDetailsService.loadUserByUsername("admin");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUsername(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return "redirect:/user";
    }

}
