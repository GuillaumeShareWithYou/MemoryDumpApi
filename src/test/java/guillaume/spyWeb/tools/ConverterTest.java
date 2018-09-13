package guillaume.spyWeb.tools;

import guillaume.spyWeb.dto.UserSessionDto;
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
        user.setUserName(username1);
        user.setId(id1);
        user2.setUserName(username2);
        user2.setId(id2);

        var users = List.of(user, user2);
        var page = new PageImpl<>(users);
        var pageDto = Converter.map(page, UserSessionDto.class);

        var content = pageDto.getContent();
        assertEquals(2, content.size());
        assertEquals(username1, content.get(0).getUserName());
        assertEquals(username2, content.get(1).getUserName());
    }

    @Test
    public void testMap() {
        var user = new User();
        user.setUserName("gui");
        var userDto = Converter.map(user, UserSessionDto.class);

        assertEquals("gui", userDto.getUserName());
    }
}