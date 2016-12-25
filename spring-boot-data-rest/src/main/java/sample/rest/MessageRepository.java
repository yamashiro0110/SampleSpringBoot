package sample.rest;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sample.model.Message;

import java.util.List;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@RepositoryRestResource(path = "messages")
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    List<Message> findAll();
}
