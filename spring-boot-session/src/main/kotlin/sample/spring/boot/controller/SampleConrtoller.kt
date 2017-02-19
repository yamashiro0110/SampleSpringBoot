package sample.spring.boot.controller

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpSession

const val sessionName = "session_value"

/**
 * Created by yamashiro-r on 2017/02/19.
 */
@RestController
@RequestMapping
@SessionAttributes(sessionName)
class SampleConrtoller {

    @ModelAttribute(sessionName)
    private fun sessionValue() = "time is ${LocalDateTime.now()}"

    @GetMapping
    fun hello() = "hello spring-boot-session!"

    @GetMapping("time")
    fun time(@ModelAttribute(sessionName) value: String) = "session_value: $value"

    @GetMapping("session")
    fun session(httpSession: HttpSession) = "sessionId: ${httpSession.id}"

    @GetMapping("create-session")
    fun createSession(): String {
        val userAuthentication = UsernamePasswordAuthenticationToken("user", "password")
        SecurityContextHolder.getContext().authentication = userAuthentication
        return "set authenticated"
    }
}
