package sample.boot.web.controller.user.api;

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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/find")
public class UserFindApi {

    @Resource
    private UserFindService userFindService;

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<SelectUserResult> findBy(final String name) {
        return this.userFindService.findByName(name).stream()
                .map(SelectUserResult::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User user(@PathVariable("id") final User user) {
        return user;
    }

    @AllArgsConstructor
    @Data
    @Builder
    private static class SelectUserResult {
        private Long id;
        private String text;

        SelectUserResult(final User user) {
            this.id = user.getId();
            this.text = user.getName();
        }
    }

}
