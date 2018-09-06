package guillaume.spyWeb.controller;

import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.exception.UserNotConnectedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

public class AbstractController {

    protected User getUserSession() {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(user instanceof User)) {
            throw new UserNotConnectedException();
        }
        return (User) user;
    }
}
