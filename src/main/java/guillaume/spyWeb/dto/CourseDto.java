package guillaume.spyWeb.dto;

public class CourseDto extends AuditDto{
    private Long id;

    private String title;

    private TopicDto topic;

    private String content;

    private UserSessionDto user;

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

    public UserSessionDto getUser() {
        return user;
    }

    public void setUser(UserSessionDto user) {
        this.user = user;
    }

}
