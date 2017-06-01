package com.example.springbootsessionredis.controller;

import com.example.springbootsessionredis.model.SampleSessionData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yamashiro-r on 2017/05/15.
 */
@Controller
@RequestMapping("/session")
@SessionAttributes("sessionData")
public class SessionController {

    @ModelAttribute("sessionData")
    SampleSessionData sessionData(@ModelAttribute("sessionData") SampleSessionData sessionData) {
        return sessionData;
    }

    @GetMapping
    String form() {
        return "form";
    }

    @PostMapping
    String post(@ModelAttribute("sessionData") SampleSessionData sessionData, Model model) {
        model.addAttribute("sessionData", sessionData);
        return "form";
    }

    @GetMapping("invalid")
    @ResponseBody
    String invalid() {
        return "invalid session";
    }

    @GetMapping("error/auth")
    @ResponseBody
    String authError() {
        return "error authentication";
    }
}
