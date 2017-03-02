package sample.spring.boot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import sample.spring.boot.models.PostRepository

@SpringBootApplication
open class SpringBootMultiprojectMasterApplication {

    @Autowired
    lateinit var postRepository: PostRepository

    @Bean
    fun init(): CommandLineRunner {
        return CommandLineRunner {
            postRepository.findAll().forEach { println("post is ${it.id}, ${it.content}") }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootMultiprojectMasterApplication::class.java, *args)
}
