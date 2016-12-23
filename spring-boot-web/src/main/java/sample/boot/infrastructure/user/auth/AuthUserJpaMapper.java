package sample.boot.infrastructure.user.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sample.boot.domain.model.user.auth.AuthUser;

/**
 * Created by yamashiro-r on 2016/12/10.
 */
@Repository
public interface AuthUserJpaMapper extends JpaRepository<AuthUser, Long> {
    AuthUser findByMail(String mail);

    AuthUser findByMailAndHashedPassword(String mail, String hashedPassword);

}
