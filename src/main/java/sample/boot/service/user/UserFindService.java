package sample.boot.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sample.boot.domain.user.address.Address;
import sample.boot.domain.user.User;
import sample.boot.repository.user.UserRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFindService {

    @Resource
    private UserRepository userRepository;

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

    public List<User> findByName(String name) {
        return userRepository.findByNameStartingWith(name);
    }

    private Specification<User> address(Address address) {
        return new UserSpecificationService(address).specification();
    }

}
