package guillaume.spyWeb.dto;

import guillaume.spyWeb.entity.Role;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private String username;
    private Long id;
    private String addressStreet;
    private List<String> rolesLabel;


    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public List<String> getRolesLabel() {
        return rolesLabel;
    }

    public void setRolesLabel(List<String> rolesLabel) {
        this.rolesLabel = rolesLabel;
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

