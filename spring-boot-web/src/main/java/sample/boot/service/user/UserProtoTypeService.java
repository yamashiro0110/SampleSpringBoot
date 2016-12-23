package sample.boot.service.user;

import org.springframework.stereotype.Service;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserFactory;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserProtoTypeService {
    @Resource
    private UserFactory userFactory;

    public User prototype() {
        return this.userFactory.create();
    }
}
