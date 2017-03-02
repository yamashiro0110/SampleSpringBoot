package sample.spring.boot.models

import javax.persistence.*

/**
 * Created by yamashiro-r on 2017/03/03.
 */
@Entity
class Post(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        @Column
        val content: String = ""
)
