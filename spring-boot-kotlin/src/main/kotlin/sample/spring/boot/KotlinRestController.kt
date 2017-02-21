package sample.spring.boot

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.annotation.Resource

/**
 * Created by yamashiro-r on 2017/02/14.
 */
@Controller
@RequestMapping("/api")
open class KotlinRestController {
    @Resource(name = "sample_message")
    private var message = ""

    @Value("\${sample.kotlin.msg.any:any message none...}")
    private var anyMessage = ""

    @GetMapping
    @ResponseBody
    fun hello() = "message is ${this.message}"

    @GetMapping("any")
    @ResponseBody
    fun any() = "anyMessage is ${this.anyMessage}"
}
