package guillaume.spyWeb.security.service;

import com.sun.deploy.xml.BadTokenException;
import guillaume.spyWeb.security.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import java.util.Calendar;
import java.util.Date;

import static guillaume.spyWeb.security.SecurityConstants.EXPIRATION_TIME;
import static guillaume.spyWeb.security.SecurityConstants.SECRET;
import static guillaume.spyWeb.security.SecurityConstants.TOKEN_PREFIX;

public class TokenService {

    /**
     * Generate a cookie secured, containing the token encrypted with HS256
     * based on the user name.
     *
     * @param username
     * @return
     */
    public static Cookie generateCookieWithToken(String username) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.SECOND, EXPIRATION_TIME);
        Date expiration = c.getTime();

        String token = Jwts.builder().setSubject(username)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        Cookie cookie = new Cookie("token", token);
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
