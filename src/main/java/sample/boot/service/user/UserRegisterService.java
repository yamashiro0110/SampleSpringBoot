package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.repository.user.UserRepository;
import sample.boot.domain.user.User;

import javax.annotation.Resource;

@Service
public class UserRegisterService {
    @Resource
    private UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

}
