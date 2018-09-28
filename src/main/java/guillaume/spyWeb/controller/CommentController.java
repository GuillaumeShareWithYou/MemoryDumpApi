package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CommentDto;
import guillaume.spyWeb.exception.ForbiddenException;
import guillaume.spyWeb.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("isAuthenticated()")
public class CommentController extends AbstractController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Create a comment for a course
     * @param id course id
     * @param commentDto
     */
    @PostMapping("/courses/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCommentToCourse(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        commentService.addCommentToCourse(id, commentDto);
    }

    /**
     * Create a comment for a comment
     * @param id comment id
     * @param commentDto
     */
    @PostMapping("/comments/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCommentToComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        commentService.addCommentToComment(id, commentDto);
    }

    /**
     * Pagination of comments for a course
     * @param id course id
     * @param pageable
     * @return
     */
    @GetMapping("/courses/{id}/comments")
    public Page<CommentDto> getCommentsByCourse(@PathVariable Long id, Pageable pageable) {
        return commentService.getByCourse(id, pageable);
    }

    /**
     * Pagination of comments of a comment
     * @param id comment id
     * @param pageable
     * @return
     */
    @GetMapping("/comments/{id}/comments")
    public Page<CommentDto> getCommentsByComment(@PathVariable Long id, Pageable pageable) {
        return commentService.getByComment(id, pageable);
    }

    /**
     * Update a comment
     * Only if the user is the owner
     * @param id comment id
     * @param commentDto the comment with content attribute
     * @return the comment updated
     */
    @PutMapping(value = "/comments/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommentDto updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        var comment = commentService.findEntityById(id);
        var userId = getUserSession().getId();
        if (! userId.equals(comment.getUser().getId())) {
            throw new ForbiddenException(String.format("The user with id %d can't modify the comment with id %d because he is not the owner.",
                    userId, comment.getId()));
        }
        return commentService.updateComment(id, commentDto);
    }

    /**
     * Delete a comment and its related comments
     * Only if the user is the owner or is an admin
     * @param id id of the comment to delete
     */
    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Long id) {
        var comment = commentService.findEntityById(id);
        var user = getUserSession();
        if (! user.getId().equals(comment.getUser().getId()) && ! user.isAdmin()) {
            throw new ForbiddenException(String.format("The user with id %d can't delete the comment with id %d because he is not the owner and he is not an admin.",
                    user.getId(), comment.getId()));
        }
        commentService.deleteById(id);

    }
}
