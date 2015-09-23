package sample.service.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import sample.domain.user.auth.AuthUser;
import sample.domain.user.User;
import sample.domain.user.UserSummary;
import sample.repository.user.UserSummaryRepository;

import javax.annotation.Resource;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class UserSummaryService {

    @Resource
    private UserSummaryRepository userSummaryRepository;

    public List<UserSummary> findAll() {
        return userSummaryRepository.findAll(Specifications.where(specification()));
    }

    private Specification<UserSummary> specification() {
        return ((root, query, cb) -> {
            Root<User> userRoot = query.from(User.class);
            Root<AuthUser> authUserRoot = query.from(AuthUser.class);

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
