package guillaume.spyWeb.security.service;

import guillaume.spyWeb.security.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User create(User user);

    User findById(Long id);
}
