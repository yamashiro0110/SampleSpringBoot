package sample.domain.models.sub;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SubTableRepository {
    @PersistenceContext(unitName = "sub_unit")
    private EntityManager entityManager;

    public List<SubTable> findAll() {
        return this.entityManager
                .createQuery("select t from SubTable t order by t.id", SubTable.class)
                .getResultList();
    }

}
