package sample.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import sample.user.address.domain.Address;
import sample.user.domain.User;
import sample.user.repository.UserRepository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

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

    public Page<User> findAll(int page) {
        return userRepository.findAll(new PageRequest(page, 5));
    }

    public User findBy(Long id) {
        return userRepository.findOne(id);
    }

    public Page<User> findByAddress(Address address) {
        return userRepository.findAll(address(address), new PageRequest(0, 5));
    }

    public void register(User user) {
        userRepository.save(user);
    }

    public boolean exist(User user) {
        return userRepository.findOne(user.getId()) != null;
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    private Specification<User> address(Address address) {
        return new UserSpecificationService(address).specification();
    }

}
