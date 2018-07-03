package guillaume.spyWeb.security;

import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.service.TokenService;
import guillaume.spyWeb.service.UserService;
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

import static guillaume.spyWeb.security.SecurityConstants.COOKIE_TOKEN_NAME;

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

        if (!request.getRequestURI().equals(SecurityConstants.SIGN_UP_URL)) {
            var usernamePasswordAuth = getAuthenticationToken(request, response);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuth);
        }

        chain.doFilter(request, response);

    }

    /**
     * Authentication by token in cookie
     *
     * @param request
     * @param response
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() == null) {
            response.setStatus(401);
            return null;
        }
        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(COOKIE_TOKEN_NAME)) {
                token = cookie.getValue();
                break;
            }
        }

        try {

            var username = TokenService.getUsernameFromToken(token);
            var user = (User) userService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        } catch (Exception e) {

            response.setStatus(401);
            e.printStackTrace();

            return null;
        }


    }

}
