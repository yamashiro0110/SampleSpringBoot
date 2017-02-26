package sample.spring.boot.models

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by yamashiro-r on 2017/02/26.
 */
@Entity
@Table(name = "post")
class Post(content: String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0L

    @Column(name = "content", nullable = false)
    var content = content

    @Column(name = "created", nullable = false)
    var created = LocalDateTime.now()

    @Version
    var version = 0
}
