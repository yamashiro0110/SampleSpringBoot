package sample.boot.web.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sample.boot.domain.model.user.User;
import sample.boot.service.user.UserProtoTypeService;
import sample.boot.service.user.UserRegisterService;
import sample.boot.service.user.auth.AuthUserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
@SessionAttributes(value = {"user"})
public class UserRegisterController {

    @Resource
    private UserProtoTypeService userProtoTypeService;

    @Resource
    private UserRegisterService userRegisterService;

    @Resource
    private AuthUserService authUserService;

    @RequestMapping(value = "input", method = RequestMethod.GET)
    public String form(@ModelAttribute("user") final User user, final Model model) {
        model.addAttribute("user", user);
        model.addAttribute("readonly", false);
        model.addAttribute("isConfirm", false);
        return "user/form";
    }

    @RequestMapping(value = "input", method = RequestMethod.POST)
    public String confirm(@Validated final User user, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            return "user/form";
        }

        if (this.authUserService.exist(user.getAuthUser())) {
            model.addAttribute("msg", "登録済みのユーザーです");
            return "user/register";
        }

        model.addAttribute("user", user);
        model.addAttribute("readonly", true);
        model.addAttribute("isConfirm", true);
        return "user/form";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") final User user, final SessionStatus status, final Model model) {
        this.userRegisterService.register(user);
        status.setComplete();
        model.addAttribute("msg", "登録完了しました！");
        return "user/register";
    }

}
