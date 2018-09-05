package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserName(String userName);

    User findByEmail(String login);
}
