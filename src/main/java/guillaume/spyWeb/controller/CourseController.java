package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CommentDto;
import guillaume.spyWeb.dto.CourseDto;
import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.service.CourseService;
import guillaume.spyWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController extends AbstractController {

    private final CourseService courseService;

    private final UserService userService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public CourseDto createCourse(@RequestBody Course course, HttpServletResponse response) {
        response.setStatus(201);
        course.setUser(getUserSession());
        return courseService.create(course);
    }

    @GetMapping("")
    public List<CourseDto> getCoursesForSession() {
        return courseService.getCoursesByUser(getUserSession());
    }

    @PostMapping(value = "/{id}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CourseDto commentCourse(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return courseService.addComment(id, commentDto);
    }
}
