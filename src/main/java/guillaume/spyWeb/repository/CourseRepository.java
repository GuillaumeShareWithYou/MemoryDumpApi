package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.Course;
import guillaume.spyWeb.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Page<Course> getByUser(User userSession, Pageable pageable);
}
