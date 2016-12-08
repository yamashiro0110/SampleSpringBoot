package sample.boot.service.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserSummary;
import sample.boot.domain.model.user.UserSummaryRepository;
import sample.boot.domain.model.user.auth.AuthUser;

import javax.annotation.Resource;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserSummaryService {

    @Resource
    private UserSummaryRepository userSummaryRepository;

    public List<UserSummary> findAll() {
        return this.userSummaryRepository.findAll(Specifications.where(specification()));
    }

    private Specification<UserSummary> specification() {
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
//            query.
            return null;
        });
    }

}
