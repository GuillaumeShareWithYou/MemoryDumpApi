package guillaume.spyWeb;

import guillaume.spyWeb.security.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONString;
import org.junit.Test;

import java.security.Key;
import java.util.Date;

public class IndependantTest {


    @Test
    public void testJwt() throws Exception {
        String key = "adzaedzadazdazdazdzgzgezg";

        User user = new User();
        user.setEmail("mathieu.guillaume3@gmail.com");
        user.setUsername("guigui");
        user.setPassword("rzakroakfpa");

        ObjectMapper mapper = new ObjectMapper();
        String compactJws = Jwts.builder()
                .setSubject(mapper.writeValueAsString(user))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        System.out.println(compactJws);
    }

    @Test
    public void dateConvertor() {
        Date expirationDate = new Date(1529361256);
        boolean b = expirationDate.before(new Date());
        System.out.println(b);
    }
}
