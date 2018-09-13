package guillaume.spyWeb;

import guillaume.spyWeb.entity.Comment;
import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.entity.Role;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.repository.CommentRepository;
import guillaume.spyWeb.repository.CourseRepository;
import guillaume.spyWeb.repository.RoleRepository;
import guillaume.spyWeb.repository.UserRepository;
import guillaume.spyWeb.security.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class Starter implements CommandLineRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CourseRepository courseRepository;
    private CommentRepository commentRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public Starter(UserRepository userRepository,
                   RoleRepository roleRepository,
                   CourseRepository courseRepository,
                   CommentRepository commentRepository,
                   BCryptPasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.commentRepository = commentRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        roleRepository.save(new Role(RoleName.USER.getName()));
        var admin = roleRepository.save(new Role(RoleName.ADMIN.getName()));
        var teacher = roleRepository.save(new Role(RoleName.TEACHER.getName()));
        User user = new User();
        user.addRole(teacher);
        user.addRole(admin);
        user.setEmail("guillaume@admin.com");
        user.setUserName("guillaume");
        user.setPassword(encoder.encode("Secret"));
        user = userRepository.save(user);
        var course = new Course();
        course.setUser(user);
        course.setTitle("Hello Java");
        course.setContent("W OMEGALUL W");
        Comment comment1 = new Comment();
        comment1.setContent("haHAA");
       // comment1.setCourse(course);
        comment1.setUser(user);
        Comment comment2 = new Comment();
        comment2.setContent("haHAA 2");
       // comment2.setCourse(course);
        comment2.setUser(user);
        Comment forComment2 = new Comment();
        forComment2.setContent("what a shit comment");
        comment2.setComments(List.of(forComment2));
        course.setComments(List.of(comment1, comment2));
        courseRepository.save(course);

        System.out.print("course added to the database");
        boolean delete = false;
        if(delete) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\rcourse deleted from the database");
                    var c = courseRepository.findById(1L);
                    courseRepository.delete(c.get());
                }
            }, 10_000);
        }
    }
}
