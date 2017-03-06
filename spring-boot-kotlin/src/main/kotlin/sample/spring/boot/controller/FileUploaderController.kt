package sample.spring.boot.controller

import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import sample.spring.boot.controller.adapter.FileUploaderResponse

/**
 * Created by yamashiro-r on 2017/03/06.
 */
@Controller
@RequestMapping("/file/uploader")
class FileUploaderController {
    val logger = LoggerFactory.getLogger(FileUploaderController::class.java)

    @GetMapping
    fun default() = "file_upload/default"

    @PostMapping(produces = arrayOf(APPLICATION_JSON_VALUE))
    @ResponseBody
    fun upload(@RequestParam("qqfile") files: Array<MultipartFile>): FileUploaderResponse {
        files.forEach { logger.info("name: ${it.name}, size: ${it.size}") }

        return FileUploaderResponse(
                success = files.isNotEmpty(),
                paths = files.map { "/img/${it.originalFilename}" }
        )
    }
}
