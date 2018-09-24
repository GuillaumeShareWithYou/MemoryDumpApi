package guillaume.spyWeb.repository;

import guillaume.spyWeb.entity.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
}
