package sample.spring.boot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.session.jdbc.JdbcOperationsSessionRepository
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession

/**
 * Created by yamashiro-r on 2017/02/19.
 */
@EnableJdbcHttpSession
class HttpSessionConfig {
    @Value("\${server.session.timeout:3600}")
    var interval = 1

    @Bean
    fun jdbcSessionRepository(repository: JdbcOperationsSessionRepository): JdbcOperationsSessionRepository {
        /* セッションの有効期限を設定(秒) */
        repository.setDefaultMaxInactiveInterval(interval)
        return repository
    }

}
