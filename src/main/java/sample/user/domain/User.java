package sample.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * userテーブルのエンティティ
 */
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

    @NotNull(message = "メールアドレスが未入力です")
    @Size(min = 1, max = 100, message = "メールアドレスを入力してください")
    @Email(message = "不正なメールアドレスです")
    private String mail;

    @NotNull(message = "電話番号が未入力です")
    @Pattern(regexp = "[0-9]{11}", message = "半角数字11文字で入力してください")
    private String tel;

    @NotNull(message = "年齢が未入力です")
    @Min(value = 0, message = "年齢を入力してください")
    private Integer age;
}
