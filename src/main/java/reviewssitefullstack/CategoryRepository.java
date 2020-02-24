package reviewssitefullstack;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Collection<Category> findByReviewsContains(Review review);
	Collection<Category> findByReviewsId(Long id);

}
