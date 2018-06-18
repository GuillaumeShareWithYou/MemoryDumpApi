package guillaume.spyWeb.controller;

import guillaume.spyWeb.security.SecurityConstants;
import guillaume.spyWeb.security.entity.User;
import guillaume.spyWeb.security.service.TokenService;
import guillaume.spyWeb.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public SessionController(AuthenticationManager authenticationManager, @Qualifier("userServiceImpl") UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public User login( User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            userService.create(user);
            response.setStatus(201);
        } catch (Exception e) {
            response.sendError(400, "Username or Email already used.");
            return null;
        }

        Cookie cookie = TokenService.generateCookieWithToken(user.getUsername());
        response.addCookie(cookie);
        System.out.println(cookie.getValue());
        return user;
    }

    @GetMapping("/info")
    public Object getUser(HttpServletRequest request) {

        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }




/*

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        response.addCookie(new Cookie(SecurityConstants.COOKIE_TOKEN_NAME, ""));
        return "au revoir";
    }*/

}
