package reviewssitefullstack.repositories;

import org.springframework.data.repository.CrudRepository;

import reviewssitefullstack.models.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
