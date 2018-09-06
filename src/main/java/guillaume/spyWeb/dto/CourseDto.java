package guillaume.spyWeb.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {
    private Long id;

    private String title;

    private TopicDto topic;

    private String content;

    private UserSessionDto user;

    private List<CommentDto> comments;

    public CourseDto() {
        this.comments = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
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
}
