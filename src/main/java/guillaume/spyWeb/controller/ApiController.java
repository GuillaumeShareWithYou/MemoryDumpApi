package guillaume.spyWeb.controller;

import guillaume.spyWeb.security.entity.Role;
import guillaume.spyWeb.security.entity.User;
import guillaume.spyWeb.security.repository.RoleRepository;
import guillaume.spyWeb.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    @Autowired
    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User index() {

        return  userService.findById(1L);
    }

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/role")
    public Role role() {

        return roleRepository.findById(2L).orElse(null);
    }


}
