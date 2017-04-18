package sample.spring.boot.controller.file.download

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by yamashiro-r on 2017/04/18.
 */
@Controller
@RequestMapping("/file/download")
class FileDownloadController {
    @GetMapping
    @ResponseBody
    fun file() = ClassPathResource("data.sql")
}
