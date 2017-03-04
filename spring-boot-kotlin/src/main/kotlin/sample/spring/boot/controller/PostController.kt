package sample.spring.boot.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import sample.spring.boot.models.post.PostJpaRepository

/**
 * Created by yamashiro-r on 2017/03/04.
 */
@Controller
@RequestMapping("/post")
class PostController {
    @Autowired
    lateinit var postJpaRepository: PostJpaRepository

    @ModelAttribute("posts")
    fun posts() = postJpaRepository.findAll()

    @GetMapping
    fun list() = "post/index"
}
