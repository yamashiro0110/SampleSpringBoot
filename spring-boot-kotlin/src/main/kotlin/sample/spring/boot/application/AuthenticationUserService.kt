package sample.spring.boot.application

import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

/**
 * Created by yamashiro-r on 2017/03/25.
 */
@Service
class AuthenticationUserService {

    fun anonymousUser(): AnonymousAuthenticationToken {
        val authentication = SecurityContextHolder.getContext().authentication
        return authentication.principal as AnonymousAuthenticationToken
    }

}
