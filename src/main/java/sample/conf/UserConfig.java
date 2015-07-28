package sample.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sample.bean.User;

/**
 * Created by yamashiro-r on 15/07/28.
 */
@Configuration
public class UserConfig {

    @Bean
    public User getUser() {
        return new User("UserConfig", "getUser");
    }
}
