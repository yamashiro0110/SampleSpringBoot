package sample.service.user;

import org.springframework.stereotype.Service;
import sample.domain.user.User;
import sample.repository.user.UserRepository;

import javax.annotation.Resource;

@Service
public class UserRegisterService {
    @Resource
    private UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

}
