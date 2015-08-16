package sample.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.user.auth.service.AuthUserService;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Resource
    private AuthUserService authUserService;

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(Model model) {
        model.addAttribute("userAuth", authUserService.prototype());
        return "login/form";
    }
}
