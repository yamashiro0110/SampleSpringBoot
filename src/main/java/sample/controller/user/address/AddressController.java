package sample.controller.user.address;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sample.domain.user.address.Address;
import sample.service.user.auth.AuthUserService;
import sample.domain.user.User;
import sample.service.user.UserFindService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user/address")
public class AddressController {
    @Resource
    private UserFindService userFindService;

    @Resource
    private AuthUserService authUserService;

    @RequestMapping(value = "find", method = RequestMethod.GET)
    public String input(Model model) {
        model.addAttribute("address", new Address());
        return "user/address/find";
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    public String find(Address address, Model model) {
        final Page<User> userPage = userFindService.findByAddress(address);
        model.addAttribute("loginUser", authUserService.getUser());
        model.addAttribute("userPage", userPage);
        return "user/list";
    }

}
