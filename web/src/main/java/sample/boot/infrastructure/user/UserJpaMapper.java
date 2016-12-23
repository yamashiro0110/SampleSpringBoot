package sample.boot.infrastructure.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sample.boot.domain.model.user.User;

import java.util.List;

@Repository
public interface UserJpaMapper extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    List<User> findByNameStartingWith(String name);
}
