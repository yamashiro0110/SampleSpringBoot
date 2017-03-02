package sample.spring.boot.models

import javax.persistence.*

/**
 * Created by yamashiro-r on 2017/02/26.
 */
@Entity
@Table(name = "post")
class Post(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "post_id")
        var id: Long = 0L,
        @Column(name = "content", nullable = false)
        var content: String = ""
)
