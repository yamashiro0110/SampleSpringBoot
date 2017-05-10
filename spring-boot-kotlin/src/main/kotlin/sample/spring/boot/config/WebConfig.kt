package sample.spring.boot.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.session.data.redis.config.ConfigureRedisAction
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.nio.file.Files
import java.nio.file.Paths

val directories = arrayOf("tmp/img/apple", "tmp/img/pineapple")
val logger = LoggerFactory.getLogger(WebConfig::class.java)

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
class RedisConfig {
    @Bean
    fun redisAction() = ConfigureRedisAction.NO_OP

    @Bean("springSessionDefaultRedisSerializer")
    fun redisSerializer() = GenericJackson2JsonRedisSerializer(objectMapper())

    fun objectMapper() = ObjectMapper()
}

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.csrf().ignoringAntMatchers("/file/uploader/ajax")
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/h2-console/**", "/tmp/img/**")
    }
}

@Configuration
class WebConfig : WebMvcConfigurerAdapter() {
    fun createDirectories() {
        directories.forEach {
            val path = Paths.get(it)
            Files.createDirectories(path)
            logger.debug("create directory: $it")
        }
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        this.createDirectories()
        val paths = directories.map { Paths.get(it).toAbsolutePath().toUri().toString() }
        registry.addResourceHandler("/tmp/img/**").addResourceLocations(paths[0], paths[1])
        logger.debug("mapping path: $paths")
    }
}
