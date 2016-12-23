package sample.boot.domain.model.user.summary;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tel;
    private String mail;
    private Integer age;
}
