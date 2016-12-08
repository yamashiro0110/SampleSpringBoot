package sample.boot.repository.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.boot.Main;
import sample.boot.domain.model.user.User;
import sample.boot.domain.model.user.UserRepository;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testFindByNameStartingWith() {
        Assert.assertNotNull(this.userRepository.findOne(1L));
    }

    @Test
    public void testDelete() {
        final User user = this.userRepository.findOne(Long.valueOf(1));
        Assert.assertNotNull(user);

        this.userRepository.delete(user);
        Assert.assertNull(this.userRepository.findOne(Long.valueOf(1)));
    }
}