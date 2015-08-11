package sample.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sample.user.domain.User;
import sample.user.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Created by yamashiro-r on 15/08/10.
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = {"user"})
public class UserController {

    @Resource
    private UserService userService;

    @ModelAttribute("user")
    private User user() {
        return userService.prototype();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String form(@ModelAttribute("user") final User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("readonly", false);
        model.addAttribute("isConfirm", false);
        return "user/form";
    }

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public String confirm(@Valid final User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/user/input";
        }

        model.addAttribute("user", user);
        model.addAttribute("readonly", true);
        model.addAttribute("isConfirm", true);
        return "user/form";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, SessionStatus status) {
        userService.register(user);
        status.setComplete();
        return "user/registered";
    }

}
