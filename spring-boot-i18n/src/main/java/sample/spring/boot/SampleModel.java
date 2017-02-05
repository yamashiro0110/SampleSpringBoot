package sample.spring.boot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * Created by yamashiro-r on 2017/02/05.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SampleModel {
    @Size(max = 50, min = 3, message = "{msg.valid.name}")
    private String name;

}
