package guillaume.spyWeb.security.service;

import guillaume.spyWeb.entity.Role;
import guillaume.spyWeb.security.repository.RoleRepository;
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


    public List<Role> getAll() {
        return (List<Role>) roleRepository.findAll();
    }
}
