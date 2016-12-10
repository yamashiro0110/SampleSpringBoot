package sample.boot.domain.model.user.auth;

import org.springframework.stereotype.Repository;
import sample.boot.infrastructure.user.auth.AuthUserJpaMapper;

import javax.annotation.Resource;

@Repository
public class AuthUserRepository {
    @Resource
    private AuthUserJpaMapper authUserJpaMapper;

    public AuthUser findByMail(final String mail) {
        return this.authUserJpaMapper.findByMail(mail);
    }

    public AuthUser findByMailAndHashedPassword(final String mail, final String hashedPassword) {
        return this.authUserJpaMapper.findByMailAndHashedPassword(mail, hashedPassword);
    }
}
