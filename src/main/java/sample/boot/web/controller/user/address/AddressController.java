package sample.boot.web.controller.user.address;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sample.boot.domain.model.address.Address;
import sample.boot.domain.model.user.User;
import sample.boot.service.user.UserFindService;
import sample.boot.service.user.auth.AuthUserService;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user/address/find")
public class AddressController {
    @Resource
    private UserFindService userFindService;

    @Resource
    private AuthUserService authUserService;

    @RequestMapping(method = GET)
    public String input(final Model model) {
        model.addAttribute("address", new Address());
        return "user/address/find";
    }

    @RequestMapping(method = POST)
    public String find(final Address address, final Model model) {
        final List<User> users = this.userFindService.findByAddress(address);
        model.addAttribute("loginUser", this.authUserService.getUser());
        model.addAttribute("users", users);
        return "user/list";
    }

}
