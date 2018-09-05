package guillaume.spyWeb;

import guillaume.spyWeb.entity.Role;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.repository.RoleRepository;
import guillaume.spyWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Starter implements CommandLineRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public Starter(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

/*        Role admin = roleRepository.findByLabel("ROLE_ADMIN");
        User user = new User();
        user.addRole(admin);
        user.setEmail("guillaume@admin.com");
        user.setUserName("guillaume");
        user.setPassword(encoder.encode("Secret"));
        userRepository.save(user);*/
    }
}
