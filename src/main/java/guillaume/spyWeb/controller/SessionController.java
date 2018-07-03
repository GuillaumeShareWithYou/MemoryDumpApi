package guillaume.spyWeb.controller;

import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.service.TokenService;
import guillaume.spyWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static guillaume.spyWeb.security.SecurityConstants.COOKIE_TOKEN_PATH;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public SessionController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(User user, HttpServletResponse response) throws IOException {

        try {
            userService.create(user);
            response.setStatus(201);
        } catch (Exception e) {
            response.sendError(400, "Username or Email already used.");
            return null;
        }

        var cookie = TokenService.generateCookieWithToken(user.getUsername());
        cookie.setPath(COOKIE_TOKEN_PATH); // otherwise the path is not '/', it's '/session' and I need a unique cookie
        response.addCookie(cookie);
        System.out.println(cookie.getValue());
        return user;
    }

    @GetMapping("/info")
    public Object getUser() {

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/logout")
    @ResponseStatus(code = HttpStatus.OK)
    public String logoutSuccessful(HttpServletResponse response) {
        response.setStatus(200);
        return "bye!";
    }
}
