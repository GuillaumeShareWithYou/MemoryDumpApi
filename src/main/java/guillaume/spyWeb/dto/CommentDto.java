package guillaume.spyWeb.dto;

public class CommentDto extends AuditDto{
    private Long id;

    private UserSessionDto user;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
