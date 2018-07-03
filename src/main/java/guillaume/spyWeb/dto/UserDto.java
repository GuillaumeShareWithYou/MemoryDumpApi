package guillaume.spyWeb.dto;

import guillaume.spyWeb.entity.Connection;
import guillaume.spyWeb.entity.Role;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private String username;
    private Long id;
    private String email;
    private List<Role> roles;

    private Connection connection;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

