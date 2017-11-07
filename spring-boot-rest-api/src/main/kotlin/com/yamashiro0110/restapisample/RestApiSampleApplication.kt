package com.yamashiro0110.restapisample

import org.hibernate.validator.constraints.NotBlank
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@SpringBootApplication
class RestApiSampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(RestApiSampleApplication::class.java, *args)
}

@RestController
@RequestMapping("/v1/sample")
class SampleApiController {

    @GetMapping
    fun get() = SampleApiResponse(msg = "hello kotlin rest api sample!!", path = "v1/sample", method = "get")

    @PostMapping
    fun post(@RequestBody @Valid request: SampleApiRequest) = SampleApiResponse(request)
}

data class SampleApiRequest(
        @get:NotBlank
        var msg: String = "",
        var path: String = "",
        var method: String = ""
)

data class SampleApiResponse(
        val msg: String,
        val path: String,
        val method: String) {

    constructor(request: SampleApiRequest) : this(
            msg = request.msg,
            path = request.path,
            method = request.method
    )

}
