package sample.spring.boot.controller.form

import org.hibernate.validator.constraints.NotEmpty
import java.io.Serializable
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Created by yamashiro-r on 2017/03/19.
 */
data class MaterialForm(
        @field:Size(min = 2, max = 100)
        var name: String = "",
        @Size(min = 5, max = 300)
        var textarea: String = "",
        @Pattern(regexp = "[0-9]{4}/[0-9]{2}/[0-9]{2}")
        var datepicker: String = "",
        @Pattern(regexp = "[0-9]{2}")
        var hour: String = "",
        @Pattern(regexp = "[00|30]")
        var minutes: String = "00",
        @NotEmpty
        var select: List<String> = arrayListOf()
) : Serializable
