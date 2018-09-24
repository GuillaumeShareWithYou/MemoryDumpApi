package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CommentDto;
import guillaume.spyWeb.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@PreAuthorize("isAuthenticated()")
public class CommentController extends AbstractController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/courses/{id}/comments")
    public void addCommentToCourse(@PathVariable Long id, @RequestBody CommentDto commentDto, HttpServletResponse response) {
        commentService.addCommentToCourse(id, commentDto);
        response.setStatus(201);
    }
    @PostMapping("/comments/{id}/comments")
    public void addCommentToComment(@PathVariable Long id, @RequestBody CommentDto commentDto, HttpServletResponse response) {
        commentService.addCommentToComment(id, commentDto);
        response.setStatus(201);
    }

    @GetMapping("/courses/{id}/comments")
    public Page<CommentDto> getCommentsByCourse(@PathVariable Long id, Pageable pageable) {
        return commentService.getByCourse(id, pageable);
    }
    @GetMapping("/comments/{id}/comments")
    public Page<CommentDto> getCommentsByComment(@PathVariable Long id, Pageable pageable) {
        return commentService.getByComment(id, pageable);
    }
}
