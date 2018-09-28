package guillaume.spyWeb.controller;

import guillaume.spyWeb.dto.CourseDto;
import guillaume.spyWeb.exception.ForbiddenException;
import guillaume.spyWeb.service.CourseService;
import guillaume.spyWeb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@PreAuthorize("isAuthenticated()")
public class CourseController extends AbstractController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(value = "courses", consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@RequestBody CourseDto course) {
        return courseService.create(course, getUserSession());
    }

    @GetMapping("courses")
    public Page<CourseDto> getCoursesForUserSession(@PageableDefault Pageable pageable) {
        return courseService.getCoursesByUser(getUserSession(), pageable);
    }

    @GetMapping("courses/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getById(id);
    }


    /**
     * Delete a course
     * Only if owner or admin
     * @param id course id
     */
    @DeleteMapping("courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        var user = getUserSession();
        var course = courseService.findEntityById(id);
        if (!user.getId().equals(course.getUser().getId())) {
            throw new ForbiddenException(String.format("The user with id %d is not the owner of the course %d and is not admin so he can't delete it.",
                    user.getId(), course.getId()));
        }
        courseService.delete(id);
    }

    /**
     * Update a course only if the user is the teacher
     * @param course course content
     * @return
     */
    @PutMapping(value = "courses", consumes = APPLICATION_JSON_VALUE)
    public CourseDto updateCourse(@RequestBody CourseDto course) {
        var userId = getUserSession().getId();
        if(! userId.equals(course.getUser().getId())) {
            throw new ForbiddenException(String.format("The user with id %d can't update the course with id %d because he is not the owner.",
                    userId, course.getId()));
        }
        return courseService.update(course);
    }
}
