package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserRepository;

import javax.annotation.Resource;

@Service
public class UserDeleteService {

    @Resource
    private UserRepository userRepository;

    public void delete(final User user) {
        this.userRepository.delete(user);
    }

}
