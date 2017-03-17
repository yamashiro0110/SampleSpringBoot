package sample.spring.boot.controller.oauth

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by yamashiro-r on 2017/03/13.
 */
@Controller
@RequestMapping("/google/login")
class GoogleLoginController {
    fun login() = "oauth/google"
}
