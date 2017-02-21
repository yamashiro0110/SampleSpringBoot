package sample.spring.boot

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by yamashiro-r on 2017/02/21.
 */
@Controller
@RequestMapping("/web")
class KotlinController {

    @ModelAttribute("options")
    fun options() = arrayOf("apple", "pineapple", "apple pen")

    @GetMapping
    fun index() = "index"

    @GetMapping(params = arrayOf("selected_option"))
    fun index(@RequestParam("selected_option") option: String, model: Model): String {
        model.addAttribute("selected_option", option)
        return "index"
    }

}
