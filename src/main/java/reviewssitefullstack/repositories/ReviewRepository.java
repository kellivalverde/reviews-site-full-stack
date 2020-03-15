package reviewssitefullstack.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	Collection<Review> findByTagsContains(Tag tag);



}
