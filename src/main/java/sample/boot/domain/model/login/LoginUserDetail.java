package sample.boot.domain.model.login;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import sample.boot.domain.model.user.auth.AuthUser;

@Getter
@EqualsAndHashCode(callSuper = false)
public class LoginUserDetail extends User {
    private final AuthUser authUser;
    private final sample.boot.domain.model.user.User user;

    public LoginUserDetail(final AuthUser authUser) {
        super(authUser.getMail(), authUser.getHashedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.authUser = authUser;
        this.user = authUser.getUser();
    }
}
