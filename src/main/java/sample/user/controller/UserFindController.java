package sample.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/find")
public class UserFindController {

    @RequestMapping(method = RequestMethod.GET)
    public String select2() {
        return "user/find/select2";
    }

}
