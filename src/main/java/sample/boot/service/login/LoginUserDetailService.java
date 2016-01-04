package sample.boot.service.login;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sample.boot.domain.login.LoginUserDetail;
import sample.boot.domain.user.auth.AuthUser;
import sample.boot.repository.user.auth.AuthUserRepository;

import javax.annotation.Resource;

@Service
public class LoginUserDetailService implements UserDetailsService {

    @Resource
    private AuthUserRepository authUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByMail(username);

        if (authUser == null) {
            throw new UsernameNotFoundException("存在しないUserです");
        }

        return new LoginUserDetail(authUser);
    }
}
