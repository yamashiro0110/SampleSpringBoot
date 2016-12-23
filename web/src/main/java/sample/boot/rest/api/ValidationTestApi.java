package sample.boot.rest.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/test/valid")
public class ValidationTestApi {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Parent implements Serializable {
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate localDate;
        private String name;
        private Integer age;
        private Child child;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Child {
        private String name;
        private Integer age;
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Parent testValid(@RequestBody final Parent parent) {
        System.out.println(parent);
        return parent;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Parent testValid() {
        return new Parent(LocalDate.now(), "aaa", 25, new Child("bbb", 2));
    }
}
