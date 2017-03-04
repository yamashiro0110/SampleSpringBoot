package sample.spring.boot.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by yamashiro-r on 2017/02/22.
 */
@Controller
@RequestMapping("/oauth")
class OAuthController {
    @Value("\$sample.kotlin.oauth.facebook.app-id")
    var facebookAppId = ""

    @ModelAttribute("facebookAppId")
    fun facebookAppId() = this.facebookAppId

    @GetMapping
    fun index() = "oauth/oauth"

    @GetMapping(path = arrayOf("facebook/callback"), params = arrayOf("code"))
    fun callbackFacebook(@RequestParam("code") code: String) = "facebook callback: $code"

}
