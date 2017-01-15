package sample.domain.models.sub;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yamashiro-r on 2017/01/15.
 */
public interface JpaSubTableRepository extends JpaRepository<SubTable, Long> {
}
