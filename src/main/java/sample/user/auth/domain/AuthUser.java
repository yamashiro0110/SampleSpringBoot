package sample.user.auth.domain;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;
import sample.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    private String hashedPassword;

    @PrePersist
    public void prePersist() {
        if (StringUtils.isEmpty(password)) {
            return;
        }

        hashedPassword = BCrypt.hashpw(password, LocalDateTime.now().toString());
    }

}
