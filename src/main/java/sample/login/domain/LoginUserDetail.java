package sample.login.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import sample.user.auth.domain.AuthUser;

@Getter
@EqualsAndHashCode(callSuper = false)
public class LoginUserDetail extends User {
    private final AuthUser authUser;
    private final sample.user.domain.User user;

    public LoginUserDetail(AuthUser authUser) {
        super(authUser.getMail(), authUser.getHashedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.authUser = authUser;
        this.user = authUser.getUser();
    }
}
