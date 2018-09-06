package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
