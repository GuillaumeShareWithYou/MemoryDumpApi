package guillaume.spyWeb.security;

import java.util.Date;

public class Token {
    private String content;

    private Date expirationDate;

    public Token() {
    }

    public Token(String content, Date expirationDate) {
        this.content = content;
        this.expirationDate = expirationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
