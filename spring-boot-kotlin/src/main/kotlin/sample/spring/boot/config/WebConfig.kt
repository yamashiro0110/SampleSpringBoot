package sample.spring.boot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.session.data.redis.config.ConfigureRedisAction
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

/**
 * Created by yamashiro-r on 2017/03/12.
 */
@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
class WebConfig : WebSecurityConfigurerAdapter() {
    @Bean
    fun redisAction() = ConfigureRedisAction.NO_OP

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .anyRequest()
                .permitAll()

        http.csrf()
                .ignoringAntMatchers("/file/uploader/ajax")
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/h2-console/**")
    }

}
