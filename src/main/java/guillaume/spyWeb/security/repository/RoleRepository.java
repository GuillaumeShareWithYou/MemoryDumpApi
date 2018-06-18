package guillaume.spyWeb.security.repository;

import guillaume.spyWeb.security.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
