package sample.user.service;

import org.springframework.stereotype.Service;
import sample.user.domain.User;
import sample.user.repository.UserRepository;

import javax.annotation.Resource;

@Service
public class UserUpdateService {
    @Resource
    private UserRepository userRepository;

    public void update(User user) {
        userRepository.save(user);
    }

}
