package sample.spring.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBootSessionApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootSessionApplication::class.java, *args)
}
