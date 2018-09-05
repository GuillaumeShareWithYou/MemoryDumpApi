package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CredentialsDto;
import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.service.TokenService;
import guillaume.spyWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

import static guillaume.spyWeb.security.SecurityConstants.COOKIE_TOKEN_NAME;
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
    public UserDto register(CredentialsDto credentials, HttpServletResponse response) throws IOException {

        System.out.println(String.format("register with username : %s and password : %s and email %s",
                credentials.getUserName(),
                credentials.getPassword(),
                credentials.getEmail()));
        UserDto userDto;
        try {
            userDto = userService.create(credentials);
            response.setStatus(201);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(400, "Email or login already used.");
            return null;
        }

        var cookie = TokenService.generateCookieWithToken(userDto.getUsername());
        cookie.setPath(COOKIE_TOKEN_PATH); // otherwise the path is not '/', it's '/session' and I need a unique cookie
        response.addCookie(cookie);
        System.out.println(String.format("the cookie generated is : %s", cookie.getValue()));
        return userDto;
    }

    @GetMapping("/info")
    public Object getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/logout")
    @ResponseStatus(code = HttpStatus.OK)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        // Delete the session by setting a new cookie with age = 0
        Cookie cookie = new Cookie(COOKIE_TOKEN_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "You have been logged out successfully";
    }
}
