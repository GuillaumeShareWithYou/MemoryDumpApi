package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.ConnectionDto;
import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ip")
public class ConnectionController {

    private ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }


    @GetMapping("")
    public List<ConnectionDto> getAll() {
        return connectionService.findAllActiveConnections();
    }

    @GetMapping("/{id}/user")
    public UserDto getUserByIp(@PathVariable Long id) {
        return connectionService.getUserByConnectionId(id);
    }
}
