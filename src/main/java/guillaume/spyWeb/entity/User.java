package guillaume.spyWeb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 15)
    private String username;

    @NotNull
    private String password;

    @Email
    @Column(unique = true)
    @NotNull
    private String email;

    @OneToOne
    private Connection connection;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    private Boolean isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, isEnabled;

    public User() {
        isAccountNonExpired = true;
        isAccountNonLocked = true;
        isCredentialsNonExpired = true;
        isEnabled = true;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(roles == null)
            return null;

      return roles.stream()
              .map(Role::getLabel)
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }


    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
