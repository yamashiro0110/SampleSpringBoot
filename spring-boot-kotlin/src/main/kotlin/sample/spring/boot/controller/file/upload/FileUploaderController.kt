package sample.spring.boot.controller.file.upload

import com.fasterxml.jackson.annotation.JsonProperty
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * Created by yamashiro-r on 2017/03/06.
 */
@Controller
@RequestMapping("/file/uploader")
class FileUploaderController {
    val logger = LoggerFactory.getLogger(FileUploaderController::class.java)

    @GetMapping
    fun default() = "file_upload/default"

    @PostMapping(path = arrayOf("ajax"), produces = arrayOf(APPLICATION_JSON_VALUE))
    @ResponseBody
    fun upload(@RequestParam("qqfile") file: MultipartFile): FileUploaderResponse {
        logger.info("name: ${file.name}, filename:${file.originalFilename}, size: ${file.size}")
        return FileUploaderResponse(success = !file.isEmpty, path = "/img/${file.originalFilename}")
    }
}

data class FileUploaderResponse(
        @JsonProperty val success: Boolean,
        @JsonProperty val path: String
)
