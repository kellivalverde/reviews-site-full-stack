package reviewssitefullstack.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Collection<Category> findByReviewsContains(Review review);
	Collection<Category> findByReviewsId(Long id);

}
