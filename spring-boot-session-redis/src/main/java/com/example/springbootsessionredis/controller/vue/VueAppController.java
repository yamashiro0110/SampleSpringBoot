package com.example.springbootsessionredis.controller.vue;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vue")
public class VueAppController {

    @ModelAttribute("items")
    List<VueAppItem> items() {
        List<VueAppItem> items = new ArrayList<>();
        items.add(new VueAppItem("title 1", "msg 1"));
        items.add(new VueAppItem("title 2", "msg 2"));
        items.add(new VueAppItem("title 3", "msg 3"));
        return items;
    }

    @GetMapping
    String index(Model model) {
        model.addAttribute("title", "sample vue.js with thymeleaf");
        model.addAttribute("msg", "hello vue.js and thymeleaf!!");
        return "vue/app";
    }

    @Data
    static class VueAppItem {
        String title;
        String msg;

        VueAppItem(final String title, final String msg) {
            this.title = title;
            this.msg = msg;
        }
    }

}
