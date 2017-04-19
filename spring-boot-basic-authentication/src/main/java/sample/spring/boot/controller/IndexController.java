package sample.spring.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yamashiro-r on 2017/04/19.
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    String index() {
        return "ok";
    }

}
