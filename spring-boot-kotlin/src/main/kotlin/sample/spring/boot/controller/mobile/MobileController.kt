package sample.spring.boot.controller.mobile

import org.springframework.mobile.device.Device
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping

/**
 * Created by yamashiro-r on 2017/02/25.
 */
@Controller
@RequestMapping("/mobile")
class MobileController {

    @ModelAttribute("device")
    fun device(device: Device) = device

    @ModelAttribute("deviceName")
    fun deviceName(device: Device) = device.devicePlatform.name

    @GetMapping
    fun index() = "mobile/mobile"

}
