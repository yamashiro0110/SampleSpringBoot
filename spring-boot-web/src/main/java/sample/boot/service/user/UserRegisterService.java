package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserFactory;
import sample.boot.domain.model.user.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class UserRegisterService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserFactory userFactory;

    public User prototype() {
        return this.userFactory.create();
    }

    @Transactional
    public void register(final User user) {
        this.userRepository.save(user);
    }

}
