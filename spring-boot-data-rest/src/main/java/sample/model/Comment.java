package sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(insertable = false, updatable = false)
    private String comment;

    @Column(name = "name", insertable = false, updatable = false)
    private String commentUser;
}
