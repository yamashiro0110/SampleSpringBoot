package sample.user.auth.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sample.login.domain.LoginUserDetail;
import sample.user.auth.domain.AuthUser;
import sample.user.auth.repository.AuthUserRepository;
import sample.user.domain.User;

import javax.annotation.Resource;

@Service
public class UserAuthService {
    @Resource
    private AuthUserRepository authUserRepository;

    public AuthUser prototype() {
        return new AuthUser();
    }

    public boolean exist(AuthUser authUser) {
        return authUserRepository.findByMailAndHashedPassword(authUser.getMail(), authUser.getHashedPassword()) != null;
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
