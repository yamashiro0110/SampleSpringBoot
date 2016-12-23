package sample.boot.domain.model.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import sample.boot.domain.model.address.Address;
import sample.boot.infrastructure.user.UserJpaMapper;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserRepository {
    @Resource
    private UserJpaMapper userJpaMapper;
    @Resource
    private UserSpecFactory userSpecFactory;

    public User findById(final Long id) {
        return this.userJpaMapper.findOne(id);
    }

    public List<User> findAll() {
        return this.userJpaMapper.findAll();
    }

    public Page<User> findAll(final PageRequest pageRequest) {
        return this.userJpaMapper.findAll(pageRequest);
    }

    public List<User> findByAddress(final Address address) {
        final Specification<User> specification = this.userSpecFactory.create(address);
        return this.userJpaMapper.findAll(specification);
    }

    public List<User> findByName(final String name) {
        return this.userJpaMapper.findByNameStartingWith(name);
    }

    public void save(final User user) {
        this.userJpaMapper.save(user);
    }

    public void delete(final User user) {
        this.userJpaMapper.delete(user);
    }

}
