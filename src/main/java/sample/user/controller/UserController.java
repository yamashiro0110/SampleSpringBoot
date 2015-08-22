package sample.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sample.user.auth.service.AuthUserService;
import sample.user.domain.User;
import sample.user.service.UserService;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = {"user"})
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AuthUserService authUserService;

    @ModelAttribute("user")
    private User user() {
        User user = userService.prototype();
        user.setAuthUser(authUserService.prototype());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<User> userPage = userService.findAll(0);
        model.addAttribute("loginUser", authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

    @RequestMapping(value = "list/{page}", method = RequestMethod.GET)
    public String list(@PathVariable(value = "page") int page, Model model) {
        Page<User> userPage = userService.findAll(page);
        model.addAttribute("loginUser", authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

    @RequestMapping(value = "input", method = RequestMethod.GET)
    public String form(@ModelAttribute("user") final User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("readonly", false);
        model.addAttribute("isConfirm", false);
        return "user/form";
    }

    @RequestMapping(value = "input", method = RequestMethod.POST)
    public String confirm(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/form";
        }

        if (authUserService.exist(user.getAuthUser())) {
            model.addAttribute("msg", "登録済みのユーザーです");
            return "user/register";
        }

        model.addAttribute("user", user);
        model.addAttribute("readonly", true);
        model.addAttribute("isConfirm", true);
        return "user/form";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, SessionStatus status, Model model) {
        userService.register(user);
        status.setComplete();
        model.addAttribute("msg", "登録完了しました！");
        return "user/register";
    }

}
