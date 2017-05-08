package sample.spring.boot.controller.session

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * Created by yamashiro-r on 2017/03/12.
 */
@Controller
@RequestMapping("/session")
@SessionAttributes("userName", "email", "userInfo")
class SessionController {

    @ModelAttribute("userName")
    fun userName(@ModelAttribute("userName") userName: String) = userName

    @GetMapping
    fun session() = "session/name"

    @GetMapping(params = arrayOf("name", "email"))
    fun setName(
            @RequestParam("name") name: String,
            @RequestParam("email") email: String,
            model: Model): String {
        model.addAttribute("userName", name)
        model.addAttribute("email", email)
        model.addAttribute("userInfo", UserInfo(name, email))
        return "session/name"
    }

    @GetMapping("json")
    @ResponseBody
    fun userInfo(@ModelAttribute("userInfo") userInfo: UserInfo) = userInfo
}

data class UserInfo(
        val name: String = "",
        val email: String = "",
        val enable: Boolean = true
)
