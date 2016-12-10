package sample.boot.infrastructure.user.summary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sample.boot.domain.model.user.summary.UserSummary;

import java.util.List;

/**
 * Created by yamashiro-r on 2016/12/10.
 */
@Repository
public interface UserSummaryJpaMapper extends JpaRepository<UserSummary, Long>, JpaSpecificationExecutor<UserSummary> {
    @Query("select user " +
            "from User user " +
            "inner join AuthUser authUser on user.id = authUser.id ")
    List<UserSummary> findAll();
}
