package sample.boot.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import sample.boot.exception.ErrorHandleException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class WebConfig {

    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/api/hoge");
        return bean;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ErrorHandleException.class)
    public Map<String, String> handleError() {
        final Map<String, String> result = new HashMap<>();
        result.put("status", "error");
        result.put("message", "error handle");
        return result;
    }

}
