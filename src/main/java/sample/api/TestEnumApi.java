package sample.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sample.domain.test.TestDomain;

@RestController
@RequestMapping("/api/enum/test/{id}")
public class TestEnumApi {

    @RequestMapping(method = RequestMethod.GET)
    public TestDomain testDomain(@PathVariable("id") TestDomain testDomain) {
        return testDomain;
    }
}
