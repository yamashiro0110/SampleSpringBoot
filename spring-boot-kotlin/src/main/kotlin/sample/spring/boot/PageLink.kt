package sample.spring.boot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by yamashiro-r on 2017/03/04.
 */
@Configuration
class PageLink(val path: String = "",
               val text: String = "",
               val icon: String = "description") {

    @Bean("pageLinks")
    fun pageLinks() = arrayListOf(
            PageLink(path = "/h2-console", text = "h2-console"),
            PageLink(path = "/mobile", text = "mobile", icon = "smartphone"),
            PageLink(path = "/basic/authentication", text = "Basic Authentication", icon = "security"),
            PageLink(path = "/facebook/login", text = "facebook login", icon = "security"),
            PageLink(path = "/google/login", text = "google login", icon = "security"),
            PageLink(path = "/yahoo/login", text = "yahoo login", icon = "security"),
            PageLink(path = "/form/material", text = "form", icon = "input"),
            PageLink(path = "/cms", text = "cms", icon = "web_asset"),
            PageLink(path = "/post", text = "post"),
            PageLink(path = "/file/uploader", text = "file-uploader", icon = "attach_file"),
            PageLink(path = "/file/download", text = "file-download", icon = "attach_file"),
            PageLink(path = "/zipcode", text = "zipcode", icon = "local_post_office"),
            PageLink(path = "/session", text = "session", icon = "storage")
    )

}
