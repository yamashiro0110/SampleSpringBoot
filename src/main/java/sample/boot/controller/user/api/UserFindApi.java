package sample.boot.controller.user.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.boot.domain.model.user.User;
import sample.boot.service.user.UserFindService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user/find")
public class UserFindApi {

    @Resource
    private UserFindService userFindService;

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<UserSelect2Dto> findBy(final String name) {
        final List<User> users = this.userFindService.findByName(name);
        final List<UserSelect2Dto> userSelect2Dtos = new ArrayList<>();
        users.forEach(user1 -> userSelect2Dtos.add(
                UserSelect2Dto.builder()
                        .id(user1.getId())
                        .text(user1.getName()).build()));
        return userSelect2Dtos;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User user(@PathVariable("id") final User user) {
        return user;
    }

    @AllArgsConstructor
    @Data
    @Builder
    private static class UserSelect2Dto {
        Long id;
        String text;
    }

}
