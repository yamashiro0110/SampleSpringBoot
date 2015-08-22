package sample.user.domain;

import lombok.*;
import sample.user.address.domain.Address;
import sample.user.auth.domain.AuthUser;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "名前が未入力です")
    @Size(min = 1, max = 100, message = "名前を入力してください")
    private String name;

    @NotNull(message = "電話番号が未入力です")
    @Pattern(regexp = "[0-9]{11}", message = "半角数字11文字で入力してください")
    private String tel;

    @NotNull(message = "年齢が未入力です")
    @Min(value = 0, message = "年齢を入力してください")
    private Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @Valid
    private AuthUser authUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Address address;

}
