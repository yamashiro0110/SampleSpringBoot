package sample; /**
 * Created by yamashiro-r on 15/07/28.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sample.bean.User;

import static java.lang.System.out;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        testMvc(args);
    }

    private static void printLine() {
        out.println("*******************");
    }

    private static void testMvc(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private static void testContext(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(Main.class, args)) {
            out.println();
            printLine();
            Main main = context.getBean(Main.class);
            main.hello();

            printLine();
            User user = context.getBean(User.class);
            out.println(user);

            printLine();
            out.println();
        }
    }


    public void hello() {
        out.println("Hello Spring Boot!!");
    }

//    @Bean
//    public User getUser() {
//        return new User("spring", "boot");
//    }

}
