package sample.boot.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sample.boot.exception.TestErrorHandleException;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WebConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/api/hoge");
        bean.setFilter(new TestFilter());
        return bean;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(TestErrorHandleException.class)
    public Map<String, String> handleError() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "error");
        result.put("message", "error handle");
        return result;
    }

    private class TestFilter extends BasicAuthenticationFilter {

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            throw new TestErrorHandleException("exception from test filter");
        }
    }

}
