package guillaume.spyWeb;

import guillaume.spyWeb.security.entity.User;
import guillaume.spyWeb.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpyWebApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpyWebApplication.class, args);
    }

/*    @Autowired
    UserService userService;

   @Bean
    public CommandLineRunner run() throws Exception {
        return args -> {
            User user = new User();
            user.setPassword("12344");
            user.setUsername("titi");
            user.setEmail("titi@mail.com");
            userService.create(user);
            System.out.println(user.getPassword());
        };
    }*/
}
