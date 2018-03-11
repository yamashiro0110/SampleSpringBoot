package com.example.springbootsessionredis.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * Created by yamashiro-r on 2017/05/25.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("userToken")
public class UserController {
    @Resource
    private ObjectMapper objectMapper;

    @ModelAttribute("userInfo")
    String userInfo(@AuthenticationPrincipal UserDetails userDetails) throws JsonProcessingException {
        return this.objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(userDetails);
    }

    @ModelAttribute("userToken")
    String userToken(@ModelAttribute("userToken") String userToken) {
        return userToken;
    }

    @GetMapping(path = {"", "default"})
    String user() {
        return "user";
    }

    @GetMapping("custom")
    String customLoginUser() {
        return "custom_login_user";
    }

}
