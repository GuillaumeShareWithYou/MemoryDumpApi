package guillaume.spyWeb.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import java.util.Calendar;
import java.util.Date;

import static guillaume.spyWeb.security.SecurityConstants.*;

public class TokenService {

    /**
     *
     * @param username
     * @return
     */
    public static Cookie generateCookieWithToken(String username) {

        var c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, COOKIE_LIFE_DURATION);
        var expiration = c.getTime();

        var token = Jwts.builder().setSubject(username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        var cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_LIFE_DURATION);
        return cookie;
    }

    /**
     * Convert the token from cookie into the user name
     *
     * @param token
     * @return the user name
     */
    public static String getUsernameFromToken(String token) {
        if(token == null || token.isEmpty())
            throw new RuntimeException("token null or empty");
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

    }

}
