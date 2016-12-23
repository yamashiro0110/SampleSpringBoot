package sample.boot.service.user.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sample.boot.domain.model.login.LoginUserDetail;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.auth.AuthUser;
import sample.boot.domain.model.user.auth.AuthUserRepository;

import javax.annotation.Resource;

@Service
public class AuthUserService {
    @Resource
    private AuthUserRepository authUserRepository;

    public AuthUser prototype() {
        return new AuthUser();
    }

    public boolean exist(final AuthUser authUser) {
        return this.authUserRepository.findByMail(authUser.getMail()) != null;
    }

    public AuthUser getAuthUser() {
        return loginUserDetail().getAuthUser();
    }

    public User getUser() {
        return loginUserDetail().getUser();
    }

    private LoginUserDetail loginUserDetail() {
        return (LoginUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
