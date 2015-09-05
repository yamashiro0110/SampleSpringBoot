package sample.domain.user.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCrypt;
import sample.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class AuthUser {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "authUser")
    private User user;

    @NotNull(message = "メールアドレスが未入力です")
    @Size(min = 1, max = 100, message = "メールアドレスを入力してください")
    @Email(message = "不正なメールアドレスです")
    @Column(unique = true)
    private String mail;

    @NotNull
    @Size(min = 6, max = 12)
    @Transient
    private String password;

    @NotNull
    @Size(min = 6, max = 12)
    @Transient
    private String confirmPassword;

    private String hashedPassword;

    @PrePersist
    public void prePersist() {
        if (StringUtils.isEmpty(password)) return;
        hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @AssertTrue(message = "確認用パスワードが一致しません!!")
    public boolean isCheckPassword() {
        return StringUtils.equals(password, confirmPassword);
    }

}
