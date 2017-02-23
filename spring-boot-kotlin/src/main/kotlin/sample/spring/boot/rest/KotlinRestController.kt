package sample.spring.boot.rest

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource

/**
 * Created by yamashiro-r on 2017/02/14.
 */
@RestController
@RequestMapping("/api")
open class KotlinRestController {
    @Resource(name = "sample_message")
    private var message = ""

    @Value("\${sample.kotlin.msg.any:any message none...}")
    private var anyMessage = ""

    @GetMapping
    fun hello() = "message is ${this.message}"

    @GetMapping("any")
    fun any() = "anyMessage is ${this.anyMessage}"
}
