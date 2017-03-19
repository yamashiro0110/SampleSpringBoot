package sample.spring.boot.controller.form

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.Valid

/**
 * Created by yamashiro-r on 2017/02/21.
 */
@Controller
@RequestMapping("/form/material")
class FormController {
    val logger = LoggerFactory.getLogger(FormController::class.java)

    @ModelAttribute("form")
    fun form() = MaterialForm(
            name = "picotaro",
            textarea = "pen pineapple apple pen",
            datepicker = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
            hour = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh"))
    )

    @GetMapping
    fun material() = "form/material_form"

    @PostMapping(consumes = arrayOf(MediaType.MULTIPART_FORM_DATA_VALUE))
    fun post(@RequestParam("file") file: MultipartFile,
             @ModelAttribute("form") @Valid form: MaterialForm,
             model: Model): String {
        logger.info("post data: $form")
        model.addAttribute("form", form)
        return "form/material_form"
    }
}
