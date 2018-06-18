package guillaume.spyWeb;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpyWebApplicationTests {

	@Autowired
	BCryptPasswordEncoder encoder;

	@Test
	public void contextLoads() {
		final String s = "jeaoizjeoahjeoiajeaijeiajzeia";
		String e = encoder.encode(s);

		assertTrue( encoder.matches(s, e));
	}


}
