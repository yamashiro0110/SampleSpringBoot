package sample.spring.boot;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yamashiro-r on 2017/01/31.
 */
public interface SampleTableRepository extends JpaRepository<SampleTable, Long> {
}
