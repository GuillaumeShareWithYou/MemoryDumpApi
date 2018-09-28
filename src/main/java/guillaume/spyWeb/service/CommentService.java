package guillaume.spyWeb.service;

import guillaume.spyWeb.dto.CommentDto;
import guillaume.spyWeb.entity.Comment;
import guillaume.spyWeb.exception.NotFoundException;
import guillaume.spyWeb.repository.CommentRepository;
import guillaume.spyWeb.repository.CourseRepository;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private CourseService courseService;
    private CourseRepository courseRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,
                          CourseService courseService) {
        this.commentRepository = commentRepository;
        this.courseService = courseService;
    }


    /**
     * Save a comment for a course
     * @param id course id
     * @param commentDto comment
     */
    public void addCommentToCourse(Long id, CommentDto commentDto) {
        var comment = Converter.map(commentDto, Comment.class);
        var course = courseService.findEntityById(id);
        comment.setCourse(course);
        commentRepository.save(comment);
    }

    public void addCommentToComment(Long id, CommentDto commentDto) {
        var parent = findEntityById(id);
        var comment = Converter.map(commentDto, Comment.class);
        comment.setComment(parent);
        commentRepository.save(comment);
    }

    public Comment findEntityById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("the comment with id %d does not exists", id)));
    }

    public Page<CommentDto> getByCourse(Long id, Pageable pageable) {
        var comments = commentRepository.findByCourseId(id, pageable);
        return Converter.map(comments, CommentDto.class);
    }

    public Page<CommentDto> getByComment(Long id, Pageable pageable) {
        var comments = commentRepository.findByCommentId(id, pageable);
        return Converter.map(comments, CommentDto.class);

    }

    @Transactional
    public CommentDto updateComment(Long id, CommentDto commentDto) {
        var comment = findEntityById(id);
        comment.setContent(commentDto.getContent());
        return Converter.map(comment, CommentDto.class);
    }



    public void deleteById(Long id) {
        this.commentRepository.deleteById(id);
    }
}
