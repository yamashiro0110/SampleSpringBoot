package sample.rest.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sample.model.Message;

import java.util.List;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@RepositoryRestResource(path = "/messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    @RestResource
    List<Message> findAll();

    @RestResource(path = "names")
    List<Message> findByName(@Param("name") String name);

    @Query("select m from Message m where m.comment like %:comment%")
    @RestResource(path = "comments")
    List<Message> findByComment(@Param("comment") String comment, Pageable pageable);
}
