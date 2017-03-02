package sample.spring.boot.models

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by yamashiro-r on 2017/03/03.
 */
interface PostRepository : JpaRepository<Post, Long>
