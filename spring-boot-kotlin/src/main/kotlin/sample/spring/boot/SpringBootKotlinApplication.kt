package sample.spring.boot

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import javax.annotation.Resource

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootKotlinApplication::class.java, *args)
}

@SpringBootApplication
open class SpringBootKotlinApplication : CommandLineRunner {

    @Resource(name = "sample_message")
    private var message = ""

    override fun run(vararg args: String?) {
        println("run kotlin")
        println("message is $message")
    }

    @Bean("sample_message")
    @ConditionalOnProperty(prefix = "sample.kotlin.msg", name = arrayOf("enable"))
    open fun messageIfTrue() = "hello kotlin!!"

    @Bean("sample_message")
    open fun messageIfFalse() = "bye kotlin..."
}
