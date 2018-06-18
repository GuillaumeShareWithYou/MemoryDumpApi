package guillaume.spyWeb.security;

import com.sun.jndi.toolkit.url.Uri;
import guillaume.spyWeb.security.entity.User;
import guillaume.spyWeb.security.service.TokenService;
import guillaume.spyWeb.security.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import static guillaume.spyWeb.security.SecurityConstants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserService userService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        if (!request.getRequestURI().equals("/session/register")) {
            UsernamePasswordAuthenticationToken usernamePasswordAuth = getAuthenticationToken(request, response);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
        }

        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(COOKIE_TOKEN_NAME)) {
                token = cookie.getValue();
                break;
            }
        }

        try {

            String username = TokenService.getUsernameFromToken(token);
            User user = (User) userService.loadUserByUsername(username);

           return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        } catch (Exception e) {

            response.setStatus(401);
            e.printStackTrace();
            return null;
        }


    }

}
