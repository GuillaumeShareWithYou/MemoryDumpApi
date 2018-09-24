package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Page<Comment> findByCourseId(Long id, Pageable pageable);

    Page<Comment> findByParentCommentId(Long id, Pageable pageable);
}
