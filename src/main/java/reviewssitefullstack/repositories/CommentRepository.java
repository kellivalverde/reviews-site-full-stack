package reviewssitefullstack.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import reviewssitefullstack.models.Comment;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Collection<Comment> findByReviewContains(Review review);

	

}
