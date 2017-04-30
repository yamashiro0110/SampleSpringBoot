package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yamashiro-r on 2017/04/30.
 */
@RestController
@RequestMapping("/sleep")
public class SleepController {

    @GetMapping
    String sleep() throws InterruptedException {
        Thread.sleep(60000);
        return "ok";
    }

}
