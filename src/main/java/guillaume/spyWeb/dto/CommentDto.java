package guillaume.spyWeb.dto;

import java.util.List;

public class CommentDto {
    private Long id;

    private UserSessionDto user;

    private String content;

    private List<CommentDto> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public UserSessionDto getUser() {
        return user;
    }

    public void setUser(UserSessionDto user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
