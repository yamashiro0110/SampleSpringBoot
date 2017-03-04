package sample.spring.boot

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.junit4.SpringRunner
import sample.spring.boot.models.post.PostJpaRepository

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = arrayOf("classpath:sql/sample_table-1.sql", "classpath:sql/sample_table-2.sql"))
class SpringBootKotlinApplicationTests {
    @Autowired
    lateinit var postJpaRepository: PostJpaRepository

    @Test
    fun contextLoads() {
        postJpaRepository.findAll().forEach { println("post is ${it.id}, ${it.content}") }
    }

}
