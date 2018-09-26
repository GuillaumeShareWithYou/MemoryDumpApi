package guillaume.spyWeb;

import guillaume.spyWeb.entity.*;
import guillaume.spyWeb.repository.*;
import guillaume.spyWeb.security.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Component
public class Starter implements CommandLineRunner {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CourseRepository courseRepository;
    private CommentRepository commentRepository;
    private TopicRepository topicRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public Starter(UserRepository userRepository,
                   RoleRepository roleRepository,
                   CourseRepository courseRepository,
                   CommentRepository commentRepository,
                   TopicRepository topicRepository,
                   BCryptPasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.courseRepository = courseRepository;
        this.commentRepository = commentRepository;
        this.topicRepository = topicRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        // Create roles
        roleRepository.save(new Role(RoleName.USER.getName()));
        var admin = roleRepository.save(new Role(RoleName.ADMIN.getName()));
        var teacher = roleRepository.save(new Role(RoleName.TEACHER.getName()));
        var topic = topicRepository.save(new Topic("Java"));
        // Create user
        User user = new User();
        user.addRole(teacher);
        user.addRole(admin);
        user.setEmail("guillaume@admin.com");
        user.setUserName("guillaume");
        user.setPassword(encoder.encode("Secret"));
        userRepository.save(user);
        // Create course
        var course = new Course();
        course.setUser(user);
        course.setTitle("Hello Java");
        course.setContent("C OMEGALUL NTENT");
        course.setTopic(topic);
        // Comments
        Comment comment1 = new Comment();
        comment1.setContent("Nice tutorial bruh");
        comment1.setUser(user);
        Comment comment2 = new Comment();
        comment2.setContent("Thank you!");
        comment2.setUser(user);

        Comment forComment2 = new Comment();
        forComment2.setContent("what a nice comment");
        forComment2.setComment(comment2);

        courseRepository.save(course);
        comment1.setCourse(course);
        comment2.setCourse(course);

        IntStream.range(1, 11).mapToObj(e -> new Comment("comment " + e))
                .map(comment -> {
                    comment.setCourse(course);
                    comment.setUser(user);
                    return comment;
                })
                .forEach(commentRepository::save);
        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(forComment2);

        System.out.print("course added to the database");
        boolean delete = false;
        if (delete) {
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
