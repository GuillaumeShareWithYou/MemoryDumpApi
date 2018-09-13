package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
