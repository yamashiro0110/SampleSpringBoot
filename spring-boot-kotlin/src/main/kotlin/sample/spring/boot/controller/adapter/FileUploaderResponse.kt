package sample.spring.boot.controller.adapter

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by yamashiro-r on 2017/03/07.
 */
class FileUploaderResponse(
        @JsonProperty val success: Boolean = false,
        @JsonProperty val paths: List<String> = arrayListOf()
)
