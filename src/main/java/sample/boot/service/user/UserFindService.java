package sample.boot.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserRepository;
import sample.boot.domain.model.user.address.Address;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFindService {

    @Resource
    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Page<User> findAll(final int page) {
        return this.userRepository.findAll(new PageRequest(page, 5));
    }

    public User findBy(final Long id) {
        return this.userRepository.findOne(id);
    }

    public Page<User> findByAddress(final Address address) {
        return this.userRepository.findAll(address(address), new PageRequest(0, 5));
    }

    public List<User> findByName(final String name) {
        return this.userRepository.findByNameStartingWith(name);
    }

    private Specification<User> address(final Address address) {
        return new UserSpecificationService(address).specification();
    }

}
