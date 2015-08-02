package sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.bean.User;
import sample.bean.UserRepository;

import java.util.List;

/**
 * データアクセスのサンプル
 */
@RestController
@RequestMapping("/sample")
public class DataAccessSampleController {

    private static final String LF = "\n";

    @Autowired
    UserRepository repository;

    /**
     * userテーブルから全件取得
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "find_all")
    public String findAll() {
        StringBuilder builder = new StringBuilder("DBからデータを取得開始").append(LF);
        List<User> userList = repository.findAll();

        for (User user : userList) {
            builder.append(user.toString()).append(LF);
        }

        builder.append("DBからデータを取得終了").append(LF);
        return builder.toString();
    }

}
