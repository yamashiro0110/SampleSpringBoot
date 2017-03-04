package sample.spring.boot.models.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by yamashiro-r on 2017/02/26.
 */
@Repository
interface PostJpaRepository : JpaRepository<Post, Long>
