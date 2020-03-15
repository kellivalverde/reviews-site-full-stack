package reviewssitefullstack.repositories;

import org.springframework.data.repository.CrudRepository;

import reviewssitefullstack.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
