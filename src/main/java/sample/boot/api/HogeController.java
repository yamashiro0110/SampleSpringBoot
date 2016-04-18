package sample.boot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/hoge")
public class HogeController {

    @RequestMapping(method = RequestMethod.POST)
    public Map<String, String> result() {
        Map<String, String> result = new HashMap<>();
        result.put("status", "ok");
        return result;
    }
}
