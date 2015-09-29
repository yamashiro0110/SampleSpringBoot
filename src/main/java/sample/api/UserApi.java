package sample.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.domain.user.User;

@RestController
@RequestMapping("/api/user")
public class UserApi {
//    @Resource
//    private UserRepository userRepository;

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public User user(@PathVariable("userId") User user) {
        return user;
    }

}
