package sample.spring.boot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by yamashiro-r on 2017/01/31.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = GET)
    String login() {
        return "/login";
    }

}
