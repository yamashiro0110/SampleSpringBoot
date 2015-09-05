package sample.domain.user;

import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User create() {
        return User.builder().build();
    }

}
