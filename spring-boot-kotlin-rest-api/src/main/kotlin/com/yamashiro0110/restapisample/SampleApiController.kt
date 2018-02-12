package com.yamashiro0110.restapisample

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import org.hibernate.validator.constraints.NotBlank
import org.springframework.web.bind.annotation.*
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.validation.Valid

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

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class SampleApiResponse(
        val msg: String,
        val path: String,
        val method: String) {

    constructor(request: SampleApiRequest) : this(
            msg = request.msg,
            path = request.path,
            method = request.method
    )

    fun zoneJst() = ZoneId.of(ZoneId.SHORT_IDS["JST"])

    fun format() = DateTimeFormatter.ISO_DATE_TIME

    @JsonProperty
    fun isoDateTime() = ZonedDateTime.now(zoneJst()).format(format())

    @JsonProperty
    fun dateTimeOfSystemTimeZone() = ZonedDateTime.now(ZoneId.systemDefault()).format(format())
}
