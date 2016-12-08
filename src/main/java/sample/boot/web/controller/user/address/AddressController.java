package sample.boot.web.controller.user.address;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.boot.domain.model.address.Address;
import sample.boot.domain.model.user.User;
import sample.boot.service.user.UserFindService;
import sample.boot.service.user.auth.AuthUserService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user/address")
public class AddressController {
    @Resource
    private UserFindService userFindService;

    @Resource
    private AuthUserService authUserService;

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String input(final Model model) {
        model.addAttribute("address", new Address());
        return "user/address/find";
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public String find(final Address address, final Model model) {
        final Page<User> userPage = this.userFindService.findByAddress(address);
        model.addAttribute("loginUser", this.authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

}
