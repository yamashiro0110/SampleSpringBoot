package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class UserUpdateService {
    @Resource
    private UserRepository userRepository;

    @Transactional
    public void update(final User user) {
        this.userRepository.save(user);
    }

}
