package sample.user.service;

import org.springframework.stereotype.Service;
import sample.user.domain.User;
import sample.user.domain.UserFactory;

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
