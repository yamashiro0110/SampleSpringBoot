package sample.user; /**
 * Created by yamashiro-r on 15/07/28.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーション起動クラス
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        testMvc(args);
    }

    /**
     * MVCアプリケーションを起動する
     * @param args
     */
    private static void testMvc(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
