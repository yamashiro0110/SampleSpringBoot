package sample.spring.boot;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yamashiro-r on 2017/01/31.
 */
@Entity
@Table(name = "sample_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class SampleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sample_id")
    Long id;

    @Column(name = "sample_name")
    String name;

    @Column(name = "post")
    String post;

    @Column(name = "created")
    Date created;
}
