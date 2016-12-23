package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.summary.UserSummary;
import sample.boot.domain.model.user.summary.UserSummaryRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserSummaryService {
    @Resource
    private UserSummaryRepository userSummaryRepository;

    public List<UserSummary> findAll() {
        return this.userSummaryRepository.findAll();
    }

}
