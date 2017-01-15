package sample.domain.models.main;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by yamashiro-r on 2017/01/15.
 */
@Repository
public class MainTableRepository {
    @PersistenceContext(unitName = "main_unit")
    private EntityManager entityManager;

    public List<MainTable> findAll() {
        return this.entityManager
                .createQuery("select t from MainTable t order by t.id", MainTable.class)
                .getResultList();
    }

}
