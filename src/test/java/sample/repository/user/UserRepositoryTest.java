package sample.repository.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.Main;
import sample.domain.user.User;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testFindByNameStartingWith() {
        Assert.assertNotNull(userRepository.findOne(1L));
    }

    @Test
    public void testDelete() {
        User user = userRepository.findOne(Long.valueOf(1));
        Assert.assertNotNull(user);

        userRepository.delete(user);
        Assert.assertNull(userRepository.findOne(Long.valueOf(1)));
    }
}