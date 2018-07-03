package guillaume.spyWeb.service;

import guillaume.spyWeb.dto.ConnectionDto;
import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.Connection;
import guillaume.spyWeb.exception.NotFoundException;
import guillaume.spyWeb.repository.ConnectionRepository;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConnectionService {

    private ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }


    @Transactional
    public Connection create(String ipAddress, int port) {
        var c = new Connection();
        c.setIpAddress(ipAddress);
        c.setPort(port);
        return connectionRepository.save(c);
    }

    public List<ConnectionDto> findAllConnections() {
        return Converter.map(connectionRepository.findAll(), ConnectionDto.class);
    }

    /**
     * Get the connections having an existing user
     *
     * @return a list of the connections
     */
    public List<ConnectionDto> findAllActiveConnections() {
        return Converter.map(connectionRepository.findAllByUserNotNull(), ConnectionDto.class);
    }

    /**
     * Get an User by his connection
     *
     * @param id of the connection
     * @return the user
     */
    public UserDto getUserByConnectionId(Long id) {
        var co = connectionRepository.findById(id);
        if (!co.isPresent())
            throw new NotFoundException(String.format("the connection with id %s does not exists", id));
        var user = co.get().getUser();
        if (user == null)
            throw new NotFoundException(String.format("the connection with id %s does not have a related user", id));
        return Converter.map(user, UserDto.class);
    }
}
