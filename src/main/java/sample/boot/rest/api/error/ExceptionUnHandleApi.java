package sample.boot.rest.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * デフォルトのエラーを返す
 */
@RestController
@RequestMapping(value = "/api/test/error/2")
public class ExceptionUnHandleApi {

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> occurredUncheckdIOException() {
        throw new UncheckedIOException("test", new IOException("ハンドリングされないexception"));
    }

    @ExceptionHandler({UncheckedIOException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> error() {
        final Map<String, Object> map = new LinkedHashMap<>();
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        map.put("msg", "occurred UncheckedIOException ExceptionUnHandleTestApi");
        return map;
    }

}
