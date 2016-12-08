package sample.boot.domain.model.user.summary.spec;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.auth.AuthUser;
import sample.boot.domain.model.user.summary.UserSummary;

import javax.persistence.criteria.Root;

@Component
public class UserSummarySpecFactory {

    public Specification<UserSummary> create() {
        return ((root, query, cb) -> {
            final Root<User> userRoot = query.from(User.class);
            final Root<AuthUser> authUserRoot = query.from(AuthUser.class);

            query.multiselect(
                    userRoot.get("name"),
                    userRoot.get("tel"),
                    userRoot.get("age"),
                    authUserRoot.get("mail")
            );

            query.where(cb.equal(userRoot.get("id"), authUserRoot.get("id")));
            return cb.and(query.getRestriction()); // あってるか不明。。
        });
    }

}
