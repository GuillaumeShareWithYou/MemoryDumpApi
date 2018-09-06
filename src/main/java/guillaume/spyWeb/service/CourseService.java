package guillaume.spyWeb.service;

import guillaume.spyWeb.dto.CourseDto;
import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.repository.CourseRepository;
import guillaume.spyWeb.tools.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

