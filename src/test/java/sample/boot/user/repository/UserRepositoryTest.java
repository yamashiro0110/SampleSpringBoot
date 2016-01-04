package sample.boot.user.repository;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sample.boot.Main;
import sample.boot.service.user.UserFindService;
import sample.boot.domain.user.User;
import sample.boot.domain.user.address.Address;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class UserRepositoryTest {

    @Resource
    private UserFindService userFindService;

    @Test
    public void findByAddress() {
        Address address = Address.builder()
                .id(Long.valueOf(1))
                .postalCode("1111111")
                .prefectures("沖縄県")
                .city("那覇市")
                .address("おもろまち１−２−３")
                .building("ｘｘｘマンション")
                .build();

        Boolean.valueOf(true);

        Page<User> users = userFindService.findByAddress(address);
        users.forEach(user -> System.out.println("user:" + ReflectionToStringBuilder.toString(user)));
    }
}