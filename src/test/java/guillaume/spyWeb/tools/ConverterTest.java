package guillaume.spyWeb.tools;

import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.Role;
import guillaume.spyWeb.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class ConverterTest {

    private List<Role> roles = Arrays.asList(new Role("Comedien"), new Role("chanteur"));

    @Test
    public void test() {
        var user = new User();
        var username = "john smith";
        user.setUsername(username);
        Long id = 32L;
        user.setId(id);
        user.setRoles(roles);

        var users = List.of(user);
        var usersDto = Converter.mapAllToDto(users, UserDto.class);
        // Utilisation simple et quelque soit les types sans créer de nouveaux oonverters
        var userDto = Converter.mapToDto(user, UserDto.class);
        assertEquals(username, userDto.getUsername());
        assertEquals(id, userDto.getId());
    }
}