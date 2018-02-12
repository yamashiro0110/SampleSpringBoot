package com.example.sampleswagger.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Controller
public class ErrorApiController extends AbstractErrorController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ErrorApiController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return "/error/api";
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request, response);
        this.logger.warn("api error {}", status);
        ErrorResponse errorResponse = new ErrorResponse("error", status.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, status);
    }

    private HttpStatus getStatus(HttpServletRequest request, HttpServletResponse response) {
        int statusCode = response.getStatus();
        HttpStatus status = HttpStatus.valueOf(statusCode);

        if (Objects.nonNull(status) && !HttpStatus.OK.equals(status)) {
            return status;
        }

        return super.getStatus(request);
    }
}
