package guillaume.spyWeb.controller;

import guillaume.spyWeb.service.RoleService;
import guillaume.spyWeb.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    private RoleService roleService;


    public ApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }




}
