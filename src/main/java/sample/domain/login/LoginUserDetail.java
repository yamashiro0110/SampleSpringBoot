package sample.domain.login;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import sample.domain.user.auth.AuthUser;

@Getter
@EqualsAndHashCode(callSuper = false)
public class LoginUserDetail extends User {
    private final AuthUser authUser;
    private final sample.domain.user.User user;

    public LoginUserDetail(AuthUser authUser) {
        super(authUser.getMail(), authUser.getHashedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.authUser = authUser;
        this.user = authUser.getUser();
    }
}
