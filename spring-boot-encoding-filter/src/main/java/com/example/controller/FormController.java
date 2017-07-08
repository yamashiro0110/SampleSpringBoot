package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by yamashiro-r on 2017/07/08.
 */
@Controller
@RequestMapping("/form")
public class FormController {
    @Resource
    private ShiftJisController shiftJisController;

    @GetMapping
    String form() {
        return "form";
    }

    @PostMapping
    String post(@ModelAttribute("text") String text, Model model) {
        ShiftJisController.ResponseAdaptor responseAdaptor = this.shiftJisController.postForm(text);
        model.addAttribute("showResponse", true);
        model.addAttribute("response", responseAdaptor);
        return "form";
    }

}
