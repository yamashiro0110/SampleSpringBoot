package sample.spring.boot.controller.debug

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by yamashiro-r on 2017/05/24.
 */
@RestController("/mock")
@ConditionalOnProperty(prefix = "mock", name = arrayOf("enable"))
class MockController {

    @GetMapping
    fun index() = MockData(id = 23, value = "hogehoge")

}

data class MockData(
        var id: Long = 0,
        var value: String = ""
)
