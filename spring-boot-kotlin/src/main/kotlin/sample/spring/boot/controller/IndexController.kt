package sample.spring.boot.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by yamashiro-r on 2017/02/25.
 */
@Controller
@RequestMapping("/")
class IndexController {

    @GetMapping
    fun index() = "index"

}