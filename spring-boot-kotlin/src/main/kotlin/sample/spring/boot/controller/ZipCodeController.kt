package sample.spring.boot.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import sample.spring.boot.models.zipcode.ZipCode
import sample.spring.boot.models.zipcode.ZipCodeJpaRepository

/**
 * Created by yamashiro-r on 2017/03/04.
 */
@Controller
@RequestMapping("/zipcode")
class ZipCodeController {
    @Autowired
    lateinit var repository: ZipCodeJpaRepository

    @ModelAttribute("query")
    fun query() = ""

    @GetMapping
    fun form() = "zipcode/form"

    @GetMapping("search")
    fun search(
            @RequestParam("query") query: String,
            model: Model): String {
        val zipCodeList: List<ZipCode> = when (query.isEmpty()) {
            true -> arrayListOf()
            false -> repository.search(query)
        }

        model.addAttribute("query", query)
        model.addAttribute("zipCodeList", zipCodeList)
        return form()
    }
}
