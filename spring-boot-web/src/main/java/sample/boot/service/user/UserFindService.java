package sample.boot.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sample.boot.domain.model.address.Address;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserRepository;

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
        return this.findAll(new PageRequest(page, 5));
    }

    public Page<User> findAll(final PageRequest pageRequest) {
        return this.userRepository.findAll(pageRequest);
    }

    public User findById(final Long id) {
        return this.userRepository.findById(id);
    }

    public List<User> findByAddress(final Address address) {
        return this.userRepository.findByAddress(address);
    }

    public List<User> findByName(final String name) {
        return this.userRepository.findByName(name);
    }
}
