package sample.spring.boot.controller.auth.basic

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by yamashiro-r on 2017/04/19.
 */
@Controller
@RequestMapping("/basic/authentication")
class BasicAuthenticationController {
    @GetMapping
    fun index() = "basic/authentication/index"
}
