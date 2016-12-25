package sample.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import sample.model.Comment;

/**
 * Created by yamashiro-r on 2016/12/25.
 */
@Projection(name = "comment", types = {Comment.class})
public interface CommentProjection {
    @Value("#{target.commentUser}")
    String getName();

    String getComment();

    @Value("#{target.commentUser}: #{target.comment}")
    String getValue();
}
