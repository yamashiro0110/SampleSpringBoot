package sample.boot.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by YamashiroRyota on 16/04/06.
 */
public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("2Zav4VmW"));
    }
}
