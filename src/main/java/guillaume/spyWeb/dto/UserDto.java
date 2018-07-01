package guillaume.spyWeb.dto;

import guillaume.spyWeb.entity.Role;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private String username;
    private Long id;
    private List<Role> roles;


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
}

