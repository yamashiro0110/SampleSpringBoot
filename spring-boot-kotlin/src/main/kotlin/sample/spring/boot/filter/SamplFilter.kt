package sample.spring.boot.filter

import org.slf4j.LoggerFactory
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter

@WebFilter(urlPatterns = arrayOf("/file/*"))
class SampleFilter: GenericFilterBean() {
    private val LOG = LoggerFactory.getLogger(SampleFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        LOG.info("ファイルがDLされました ${request.remoteHost}, ${request.remoteAddr}")
        chain.doFilter(request, response)
    }

}
