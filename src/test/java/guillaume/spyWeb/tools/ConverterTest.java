package guillaume.spyWeb.tools;

import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.User;
import org.junit.Test;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
public class ConverterTest {

    @Test
    public void test() {

    }

    @Test
    public void paginationConversion() {
        var user = new User();
        var user2 = new User();
        var username1 = "john smith";
        var username2 = "jane smith";
        var id1 = 32L;
        var id2 = 35L;
        user.setUsername(username1);
        user.setId(id1);
        user2.setUsername(username2);
        user2.setId(id2);

        var users = List.of(user, user2);
        var page = new PageImpl<>(users);
        var pageDto = Converter.map(page, UserDto.class);

        var content = pageDto.getContent();
        assertEquals(2, content.size());
        assertEquals(username1, content.get(0).getUsername());
        assertEquals(username2, content.get(1).getUsername());
    }

    @Test
    public void testMap() {
        var user = new User();
        user.setUsername("gui");
        var userDto = Converter.map(user, UserDto.class);

        assertEquals("gui", userDto.getUsername());
    }
}