package sample.service.user;

import org.springframework.stereotype.Service;
import sample.domain.user.User;
import sample.domain.user.UserFactory;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserProtoTypeService {
    @Resource
    private UserFactory userFactory;

    public User prototype() {
        return userFactory.create();
    }
}
