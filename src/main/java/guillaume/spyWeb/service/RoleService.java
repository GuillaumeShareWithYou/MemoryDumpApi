package guillaume.spyWeb.service;

import guillaume.spyWeb.entity.Role;
import guillaume.spyWeb.exception.RoleNotFoundException;
import guillaume.spyWeb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(String.format("The role with id %s does not exists", id)));

    }

    public Role save(Role role) {
        System.out.println("...");
        return roleRepository.save(role);
    }
}
