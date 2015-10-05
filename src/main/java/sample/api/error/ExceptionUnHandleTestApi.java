package sample.api.error;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Map;

/**
 * デフォルトのエラーを返す
 */
@RestController
@RequestMapping(value = "/api/test/error/2")
public class ExceptionUnHandleTestApi {

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> occurredUncheckdIOException() {
        throw new UncheckedIOException("test", new IOException("ハンドリングされないexception"));
    }
}
