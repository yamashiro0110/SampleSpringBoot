package sample.user.repository;

import junit.framework.TestCase;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.Main;
import sample.user.address.domain.Address;
import sample.user.domain.User;
import sample.user.service.UserService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class UserRepositoryTest extends TestCase {
    @Resource
    private UserService userService;

    @Test
    public void findByAddress() {
        Address address = Address.builder()
                .id(new Long(1))
                .postalCode("1111111")
                .prefectures("沖縄県")
                .city("那覇市")
                .address("おもろまち１−２−３")
                .building("ｘｘｘマンション")
                .build();

        Page<User> users = userService.findByAddress(address);
        users.forEach(user -> System.out.println("user:" + ReflectionToStringBuilder.toString(user)));
    }
}
