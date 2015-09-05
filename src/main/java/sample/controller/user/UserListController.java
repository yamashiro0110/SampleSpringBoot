package sample.controller.user;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sample.service.user.auth.AuthUserService;
import sample.domain.user.User;
import sample.service.user.UserFindService;
import sample.service.user.UserProtoTypeService;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = {"user"})
public class UserListController {
    @Resource
    private UserProtoTypeService userProtoTypeService;

    @Resource
    private UserFindService userFindService;

    @Resource
    private AuthUserService authUserService;

    @ModelAttribute("user")
    private User user() {
        User user = userProtoTypeService.prototype();
        user.setAuthUser(authUserService.prototype());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        Page<User> userPage = userFindService.findAll(0);
        model.addAttribute("loginUser", authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

    @RequestMapping(value = "list/{page}", method = RequestMethod.GET)
    public String list(@PathVariable(value = "page") int page, Model model) {
        Page<User> userPage = userFindService.findAll(page);
        model.addAttribute("loginUser", authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

}
