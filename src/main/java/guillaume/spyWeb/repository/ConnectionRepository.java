package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.Connection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {

    List<Connection> findAllByUserNotNull();
}
