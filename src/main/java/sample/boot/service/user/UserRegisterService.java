package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserRepository;

import javax.annotation.Resource;

@Service
public class UserRegisterService {
    @Resource
    private UserRepository userRepository;

    public void register(final User user) {
        this.userRepository.save(user);
    }

}
