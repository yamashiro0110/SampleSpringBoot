package sample.rest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sample.model.Comment;
import sample.projection.CommentProjection;

import java.util.List;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@RepositoryRestResource(path = "/comment", excerptProjection = CommentProjection.class)
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
    @RestResource
    List<Comment> findAll();

    @RestResource(exported = false)
    @Override
    void delete(Iterable<? extends Comment> entities);

    @RestResource(exported = false)
    @Override
    void delete(Long aLong);

    @RestResource(exported = false)
    @Override
    void delete(Comment entity);
}
