package guillaume.spyWeb.service;

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
    public void findAdminById() {
        var user = userService.findById(1L);
        assertTrue(user.getRoles().stream().map(Role::getLabel).anyMatch(label -> label.equals("ROLE_ADMIN")));
    }
}