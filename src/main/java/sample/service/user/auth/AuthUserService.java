package sample.service.user.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sample.domain.login.LoginUserDetail;
import sample.domain.user.auth.AuthUser;
import sample.repository.user.auth.AuthUserRepository;
import sample.domain.user.User;

import javax.annotation.Resource;

@Service
public class AuthUserService {
    @Resource
    private AuthUserRepository authUserRepository;

    public AuthUser prototype() {
        return new AuthUser();
    }

    public boolean exist(AuthUser authUser) {
        return authUserRepository.findByMail(authUser.getMail()) != null;
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
