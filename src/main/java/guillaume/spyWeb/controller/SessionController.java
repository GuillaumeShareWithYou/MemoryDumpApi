package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CredentialsDto;
import guillaume.spyWeb.dto.UserSessionDto;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.service.TokenService;
import guillaume.spyWeb.service.UserService;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static guillaume.spyWeb.security.SecurityConstants.COOKIE_TOKEN_NAME;
import static guillaume.spyWeb.security.SecurityConstants.COOKIE_TOKEN_PATH;

@RestController
@RequestMapping("/session")
public class SessionController extends AbstractController {


    private final UserService userService;

    @Autowired
    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSessionDto register(CredentialsDto credentials, HttpServletResponse response)  {

        System.out.println(String.format("register with username : %s and password : %s and email %s",
                credentials.getUserName(),
                credentials.getPassword(),
                credentials.getEmail()));
        UserSessionDto userDto = userService.create(credentials);
        var cookie = TokenService.generateCookieWithToken(userDto.getUserName());
        cookie.setPath(COOKIE_TOKEN_PATH);
        response.addCookie(cookie);
        System.out.println(String.format("the cookie generated is : %s", cookie.getValue()));
        return userDto;
    }

    @GetMapping("/info")
    public UserSessionDto getUser() {
        return Converter.map(getUserSession(), UserSessionDto.class);
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
