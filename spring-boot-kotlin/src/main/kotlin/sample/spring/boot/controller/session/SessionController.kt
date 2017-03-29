package sample.spring.boot.controller.session

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * Created by yamashiro-r on 2017/03/12.
 */
@Controller
@RequestMapping("/session")
@SessionAttributes("userName", "email")
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
        return "session/name"
    }
}
