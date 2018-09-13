package guillaume.spyWeb.service;

import guillaume.spyWeb.dto.CommentDto;
import guillaume.spyWeb.dto.CourseDto;
import guillaume.spyWeb.entity.Comment;
import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.repository.CourseRepository;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDto create(Course course) {
        return Converter.map(courseRepository.save(course), CourseDto.class);
    }

    public List<CourseDto> getCoursesByUser(User userSession) {
        return Converter.map(courseRepository.getByUser(userSession), CourseDto.class);
    }

    public CourseDto getById(Long id) {
        var course = courseRepository.findById(id).orElseThrow(RuntimeException::new);
        return Converter.map(course, CourseDto.class);
    }

    public CourseDto addComment(Long id, CommentDto commentDto) {
        var course = courseRepository.findById(id).orElseThrow(RuntimeException::new);
        course.addComment(Converter.map(commentDto, Comment.class));
        return Converter.map(courseRepository.save(course), CourseDto.class);
    }
}

