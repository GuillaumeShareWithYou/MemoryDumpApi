package guillaume.spyWeb.service;

import guillaume.spyWeb.dto.CourseDto;
import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.entity.User;
import guillaume.spyWeb.exception.NotFoundException;
import guillaume.spyWeb.repository.CourseRepository;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseDto create(CourseDto courseDto, User user) {
        var course = Converter.map(courseDto, Course.class);
        course.setUser(user);
        return Converter.map(courseRepository.save(course), CourseDto.class);
    }
    public CourseDto update(CourseDto courseDto) {
        var course = Converter.map(courseDto, Course.class);
        return Converter.map(courseRepository.save(course), CourseDto.class);
    }

    public Page<CourseDto> getCoursesByUser(User userSession, Pageable pageable) {
        var course = courseRepository.getByUser(userSession, pageable);
        return Converter.map(course, CourseDto.class);
    }

    public CourseDto getById(Long id) {
        var course = courseRepository.findById(id).orElseThrow(RuntimeException::new);
        return Converter.map(course, CourseDto.class);
    }


    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    public Course findEntityById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("the course with id %d does not exists", id)));
    }
}

