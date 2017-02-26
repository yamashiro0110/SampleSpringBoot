package sample.spring.boot.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by yamashiro-r on 2017/02/26.
 */
@Controller
@RequestMapping("/cms")
class CMSController {
    val logger = LoggerFactory.getLogger(CMSController::class.java)

    @GetMapping
    fun cms() = "cms"

    @PostMapping
    fun post(@RequestParam("content") content: String, model: Model): String {
        logger.info("content: $content")
        model.addAttribute("result", content)
        return "cms_result"
    }

}
