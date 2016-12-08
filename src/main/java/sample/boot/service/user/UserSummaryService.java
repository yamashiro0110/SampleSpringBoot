package sample.boot.service.user;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.summary.UserSummary;
import sample.boot.domain.model.user.summary.UserSummaryRepository;
import sample.boot.domain.model.user.summary.spec.UserSummarySpecFactory;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserSummaryService {
    @Resource
    private UserSummaryRepository userSummaryRepository;
    @Resource
    private UserSummarySpecFactory userSummarySpecFactory;

    private Specification<UserSummary> specification() {
        return this.userSummarySpecFactory.create();
    }

    public List<UserSummary> findAll() {
        final Specification<UserSummary> specification = this.specification();
        return this.userSummaryRepository.findAll(Specifications.where(specification));
    }

}
