package sample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yamashiro-r on 15/07/28.
 */
@RestController
@RequestMapping("/")
public class SampleController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "Hello Spring MVC!!";
    }
}
