package sample.spring.boot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by yamashiro-r on 2017/03/04.
 */
@Configuration
class PageLink(
        val path: String = "",
        val text: String = "",
        val icon: String = "description") {

    @Bean("pageLinks")
    fun pageLinks() = arrayListOf<PageLink>(
            PageLink(path = "/h2-console", text = "h2-console"),
            PageLink(path = "/mobile", text = "mobile"),
            PageLink(path = "/oauth", text = "oauth"),
            PageLink(path = "/form/material", text = "form"),
            PageLink(path = "/cms", text = "cms"),
            PageLink(path = "/post", text = "post"),
            PageLink(path = "/zipcode", text = "zipcode")
    )

}
