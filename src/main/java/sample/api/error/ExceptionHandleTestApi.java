package sample.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ExceptionHandlerがつけられたメソッドがエラーとして返される
 */
@RestController
@RequestMapping(value = "/api/test/error/1")
public class ExceptionHandleTestApi {

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> occurredUncheckdIOException() {
        throw new UncheckedIOException("test", new IOException("test exception handling"));
    }

    @ExceptionHandler({UncheckedIOException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> error() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("msg", "occurred UncheckedIOException ExceptionHandleTestApi");
        return map;
    }
}
