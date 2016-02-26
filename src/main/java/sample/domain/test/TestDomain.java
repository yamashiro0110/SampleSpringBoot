package sample.domain.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String value;

    @Convert(converter = TestEnumConverter.class)
    private TestEnum testEnum;
}
