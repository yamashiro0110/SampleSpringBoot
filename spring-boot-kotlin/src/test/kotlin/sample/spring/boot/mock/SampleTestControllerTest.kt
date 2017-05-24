package sample.spring.boot.mock

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import sample.spring.boot.config.SecurityConfig

/**
 * Created by yamashiro-r on 2017/05/24.
 */
@RunWith(SpringRunner::class)
@WebMvcTest(SampleTestController::class)
@Import(SecurityConfig::class)
class SampleTestControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    val objectMapper = ObjectMapper()

    fun json() = objectMapper.writeValueAsString(SampleTestData(id = 100, value = "hoge"))

    @Test
    fun index() {
        this.mvc.perform(get("/test/sample"))
                .andExpect(status().isOk)
                .andExpect(content().json(this.json()))
    }

}