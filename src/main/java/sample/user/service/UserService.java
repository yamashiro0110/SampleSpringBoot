package sample.user.service;

import org.springframework.stereotype.Service;
import sample.user.domain.User;
import sample.user.repository.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by yamashiro-r on 15/08/11.
 */
@Service
@Transactional
public class UserService {
    @Resource
    private UserRepository userRepository;

    public User prototype() {
        return new User();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public boolean exist(User user) {
        return userRepository.findByMail(user.getMail()) != null;
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

}
