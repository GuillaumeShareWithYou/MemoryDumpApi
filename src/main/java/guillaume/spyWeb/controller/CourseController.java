package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CommentDto;
import guillaume.spyWeb.dto.CourseDto;
import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.service.CourseService;
import guillaume.spyWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@PreAuthorize("isAuthenticated()")
public class CourseController extends AbstractController {

    private final CourseService courseService;

    private final UserService userService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping(value = "courses", consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public CourseDto createCourse(@RequestBody Course course, HttpServletResponse response) {
        response.setStatus(201);
        course.setUser(getUserSession());
        return courseService.create(course);
    }

    @GetMapping("courses")
    public List<CourseDto> getCoursesForUserSession() {
        return courseService.getCoursesByUser(getUserSession());
    }

    @GetMapping("courses/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }


    @DeleteMapping("courses/{id}")
    public void deleteCourse(@PathVariable Long id, HttpServletResponse response) {
        try {
            courseService.delete(id);
            response.setStatus(204);
        } catch (EmptyResultDataAccessException e) {
            response.setStatus(404);
        }
    }

    @PutMapping(value = "courses", consumes = APPLICATION_JSON_VALUE)
    public CourseDto updateCourse(@RequestBody Course course) {
        return courseService.create(course);
    }
}
