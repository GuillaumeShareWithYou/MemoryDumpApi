package guillaume.spyWeb.security.service;

import guillaume.spyWeb.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getById() {
        var user = userService.findById(1L);
        var list = user.getRoles().stream().map(Role::getLabel).collect(Collectors.toList());
        assertTrue(list.contains("ROLE_ADMIN"));
    }
}