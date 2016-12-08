package sample.boot.domain.model.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByMail(String mail);

    AuthUser findByMailAndHashedPassword(String mail, String hashedPassword);
}
