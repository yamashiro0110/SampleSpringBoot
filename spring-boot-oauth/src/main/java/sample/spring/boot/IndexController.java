package sample.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yamashiro-r on 2017/02/12.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    String index() {
        return "/index";
    }

}
