package sample.boot.rest.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.boot.domain.model.user.User;

@RestController
@RequestMapping("/api/user/{userId}")
public class UserApi {
    @RequestMapping(method = RequestMethod.GET)
    public User user(@PathVariable("userId") final User user) {
        return user;
    }
}
