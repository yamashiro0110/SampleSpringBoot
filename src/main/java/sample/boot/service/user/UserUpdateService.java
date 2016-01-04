package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.user.User;
import sample.boot.repository.user.UserRepository;

import javax.annotation.Resource;

@Service
public class UserUpdateService {
    @Resource
    private UserRepository userRepository;

    public void update(User user) {
        userRepository.save(user);
    }

}
