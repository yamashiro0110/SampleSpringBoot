package sample.boot.domain.model.user.summary;

import org.springframework.stereotype.Repository;
import sample.boot.infrastructure.user.summary.UserSummaryJpaMapper;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserSummaryRepository {
    @Resource
    private UserSummaryJpaMapper userSummaryJpaMapper;

    public List<UserSummary> findAll() {
        return this.userSummaryJpaMapper.findAll();
    }

}
