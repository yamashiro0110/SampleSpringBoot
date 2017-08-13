package com.yamashiro0110.springbootactuator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yamashiro-r on 2017/08/13.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "ok");
        return map;
    }

}
