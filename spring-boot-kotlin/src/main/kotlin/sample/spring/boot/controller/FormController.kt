package sample.spring.boot.controller

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
@RequestMapping("/form")
class FormController {

    @ModelAttribute("options")
    fun options() = arrayOf("apple", "pineapple", "apple pen")

    @GetMapping
    fun index() = "form"

    @GetMapping(params = arrayOf("selected_option"))
    fun index(@RequestParam("selected_option") option: String, model: Model): String {
        model.addAttribute("selected_option", option)
        return this.index()
    }

    @GetMapping("material")
    fun material() = "material_form"

    @GetMapping(path = arrayOf("material"), params = arrayOf("selected_option"))
    fun material(@RequestParam("selected_option") option: String, model: Model): String {
        model.addAttribute("selected_option", option)
        return this.material()
    }
}
