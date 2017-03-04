package sample.spring.boot.controller

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import sample.spring.boot.models.PostImage

/**
 * Created by yamashiro-r on 2017/02/26.
 */
@Controller
@RequestMapping("/cms")
class CMSController {
    val logger = LoggerFactory.getLogger(CMSController::class.java)

    @GetMapping
    fun cms() = "cms/cms"

    @PostMapping
    fun post(@RequestParam("content") content: String, model: Model): String {
        logger.info("content: $content")
        model.addAttribute("result", content)
        return "cms/cms_result"
    }

    @PostMapping("file_upload")
    @ResponseBody
    fun fileUpload(@RequestParam("files[]") images: Array<MultipartFile>): Map<String, List<PostImage>> {
        val files = images.map { this.createImage(it) }
        return mapOf(Pair("files", files))
    }

    private fun createImage(file: MultipartFile): PostImage {
        return PostImage(
                name = file.originalFilename,
                url = "image/${file.originalFilename}",
                size = file.size
        )
    }

}
