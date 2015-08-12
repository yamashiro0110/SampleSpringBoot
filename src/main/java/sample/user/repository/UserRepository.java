package sample.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByMail(String mail);
}
