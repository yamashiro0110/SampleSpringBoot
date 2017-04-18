package sample.spring.boot

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import javax.annotation.Resource

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootKotlinApplication::class.java, *args)
}

@SpringBootApplication
@ServletComponentScan
open class SpringBootKotlinApplication : CommandLineRunner {

    private val logger = LoggerFactory.getLogger(SpringBootKotlinApplication::class.java)

    @Resource(name = "sample_message")
    private var message = ""

    override fun run(vararg args: String?) {
        logger.info("run kotlin")
        logger.info("message is $message")
    }

    @Bean("sample_message")
    @ConditionalOnProperty(prefix = "sample.kotlin.msg", name = arrayOf("enable"))
    open fun messageIfTrue() = "hello kotlin!!"

    @Bean("sample_message")
    open fun messageIfFalse() = "bye kotlin..."
}
