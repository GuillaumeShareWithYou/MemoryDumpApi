package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.Role;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.service.RoleService;
import guillaume.spyWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String index() {
        return "Home page for the admin";
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(User user) {

        return userService.save(user);
    }

    @GetMapping("/user/{id}")
    public User index(@PathVariable("id") Long userId) {

        return userService.findById(userId);
    }

    @GetMapping("/user")
    public List<UserDto> findAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/role/{id}")
    public Role findRoleById(@PathVariable("id") Long roleId) {

        return roleService.findById(roleId);
    }
}
