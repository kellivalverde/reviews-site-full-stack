package reviewssitefullstack.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Collection<Tag> findByReviewsContains(Review review);

}
