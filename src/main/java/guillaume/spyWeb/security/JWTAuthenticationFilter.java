package guillaume.spyWeb.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        var userName = request.getParameter("username");
        var password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        // Send cookie for authorization
        var cookie = TokenService.generateCookieWithToken(authResult.getName());
        response.addCookie(cookie);

        // Send user info after login
        var mapper = new ObjectMapper();
        var user = (User) authResult.getPrincipal();
        response.getWriter().write(mapper.writeValueAsString(user));
    }
}
