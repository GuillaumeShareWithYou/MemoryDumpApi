package guillaume.spyWeb.security.service;

import guillaume.spyWeb.dto.UserDto;
import guillaume.spyWeb.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User create(User user);

    User findById(Long id);

    List<UserDto> findAll();
}
