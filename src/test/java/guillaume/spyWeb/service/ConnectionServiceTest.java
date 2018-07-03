package guillaume.spyWeb.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConnectionServiceTest {

    @Autowired
    private ConnectionService connectionService;

    @Test
    public void create1() {
        var ip = "129.255.0.12";
        Integer port = 32133;
        var c = connectionService.create(ip, port);
        assertEquals(ip, c.getIpAddress());
        assertEquals(port, c.getPort());
    }

    @Test(expected = ConstraintViolationException.class)
    public void create2() {
        var ip = "-1.12.255.0";
        Integer port = 32133;
        var c = connectionService.create(ip, port);
        assertEquals(ip, c.getIpAddress());
        assertEquals(port, c.getPort());
    }

    @Test(expected = ConstraintViolationException.class)
    public void create3() {
        var ip = "129.300.32.0";
        Integer port = 32133;
        var c = connectionService.create(ip, port);
        assertEquals(ip, c.getIpAddress());
        assertEquals(port, c.getPort());
    }

    @Test(expected = ConstraintViolationException.class)
    public void create4() {
        var ip = "129.255.0";
        Integer port = 32133;
        var c = connectionService.create(ip, port);
        assertEquals(ip, c.getIpAddress());
        assertEquals(port, c.getPort());
    }

}