package sample.spring.boot;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name = "sample_id")
    Long id;

    @Column(name = "sample_name")
    String name;

    @Column(name = "created")
    Date created;
}
