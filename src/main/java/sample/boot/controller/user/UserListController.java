package sample.boot.controller.user;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sample.boot.domain.model.user.User;
import sample.boot.service.user.UserFindService;
import sample.boot.service.user.UserProtoTypeService;
import sample.boot.service.user.auth.AuthUserService;

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
        final User user = this.userProtoTypeService.prototype();
        user.setAuthUser(this.authUserService.prototype());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/user/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final Model model) {
        final Page<User> userPage = this.userFindService.findAll(0);
        model.addAttribute("loginUser", this.authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

    @RequestMapping(value = "list/{page}", method = RequestMethod.GET)
    public String list(@PathVariable(value = "page") final int page, final Model model) {
        final Page<User> userPage = this.userFindService.findAll(page);
        model.addAttribute("loginUser", this.authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

}
