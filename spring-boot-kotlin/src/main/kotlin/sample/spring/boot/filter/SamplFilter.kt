package sample.spring.boot.filter

import org.slf4j.LoggerFactory
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

@WebFilter(urlPatterns = arrayOf("/file/*"))
class FileDownloadFilter: GenericFilterBean() {
    private val LOG = LoggerFactory.getLogger(FileDownloadFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        LOG.info("ファイルがDLされました ${request.remoteHost}, ${request.remoteAddr}")
        chain.doFilter(request, response)
    }

}

@WebFilter(urlPatterns = arrayOf("/*"))
class SaveSessionAttributeFilter: GenericFilterBean() {
    private val LOG = LoggerFactory.getLogger(SaveSessionAttributeFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val servletRequest = request as HttpServletRequest
        val session = servletRequest.session
        LOG.info("sessionId:${session.id}")
        chain.doFilter(request, response)
    }

}
