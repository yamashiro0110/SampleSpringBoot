package sample.spring.boot.controller.oauth

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by yamashiro-r on 2017/03/13.
 */
@Controller
@RequestMapping("/facebook/login")
class FacebookLoginController {
    @GetMapping
    fun login() = "oauth/facebook"
}
