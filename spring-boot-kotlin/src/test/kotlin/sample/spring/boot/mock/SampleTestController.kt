package sample.spring.boot.mock

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by yamashiro-r on 2017/05/24.
 */
@RestController("/test/sample")
class SampleTestController {

    @GetMapping
    fun index() = SampleTestData(id = 100, value = "hoge")

}

data class SampleTestData(
        var id: Long = 0,
        var value: String = ""
)
