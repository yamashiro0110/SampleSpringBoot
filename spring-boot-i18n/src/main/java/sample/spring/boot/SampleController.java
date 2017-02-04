package sample.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yamashiro-r on 2017/02/04.
 */
@Controller
@RequestMapping("/sample")
public class SampleController {

    @RequestMapping(method = RequestMethod.GET)
    String index(final Model model) {
        model.addAttribute("msg", "apple pen");
        return "/index";
    }

}
