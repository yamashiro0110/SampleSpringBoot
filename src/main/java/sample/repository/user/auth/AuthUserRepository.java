package sample.repository.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.domain.user.auth.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByMail(String mail);

    AuthUser findByMailAndHashedPassword(String mail, String hashedPassword);
}
