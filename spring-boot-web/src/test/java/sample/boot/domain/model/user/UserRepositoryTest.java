package sample.boot.domain.model.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.boot.Main;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class UserRepositoryTest {
    @Resource
    private UserRepository userRepository;

    @Test
    public void testFindByNameStartingWith() {
        assertNotNull(this.userRepository.findById(1L));
    }

    @Test
    public void testDelete() {
        final User user = this.userRepository.findById(1L);
        assertNotNull(user);

        this.userRepository.delete(user);
        assertNull(this.userRepository.findById(1L));
    }
}