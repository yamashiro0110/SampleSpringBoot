package sample.spring.boot.config

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

/**
 * Created by yamashiro-r on 2017/02/19.
 */
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        /* user, passwordでログイン出来るようにする */
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                /* このURLは認証しない */
                .antMatchers("/create-session", "/login", "/logout")
                .permitAll()
                /* 他のURLは認証する */
                .anyRequest()
                .authenticated()

        http.formLogin()
                /* formログイン成功したらリダイレクトされる */
                .defaultSuccessUrl("/time")

        http.logout()
                /* logoutを実行するURL */
                .logoutRequestMatcher(AntPathRequestMatcher("/logout**"))
                /* logoutされた後にリダイレクトされる */
                .logoutSuccessUrl("/login")
    }

    override fun configure(web: WebSecurity) {
        /* セキュリティの対象から外す */
        web.ignoring().antMatchers("/h2-console/**")
    }

}
