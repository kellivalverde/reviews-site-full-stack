package reviewssitefullstack; // Mod 7 Graded Project

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.ReviewRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class JPAMappingTestRSFS {

	@Resource
	private TestEntityManager entityManager; // common practice

	// injecting repositories
	// create Interfaces with CRUD repository added
	@Resource
	private ReviewRepository reviewRepo;

	@Resource
	private CategoryRepository categoryRepo;

	@Test
	public void shouldSaveAndLoadReview() {
		Review review = reviewRepo.save(new Review("review"));// pulls Review entity from review repository -->CRUD Repo
																// saves
		long reviewId = review.getId();

		entityManager.flush(); // forces JPA to hit the database when we try to find it
		entityManager.clear();

		Optional<Review> result = reviewRepo.findById(reviewId);
		review = result.get();
		assertEquals(review.getTitle(), "review");
	}

	@Test
	public void shouldGenerateReviewId() {
		Review review = reviewRepo.save(new Review("review")); // shortcut version
		long reviewId = review.getId();

		entityManager.flush();
		entityManager.clear();

		assertThat(reviewId, is(greaterThan(0L)));
	}

	@Test
	public void shouldSaveAndLoadCategory() {
		Category category = new Category("category name", "description"); // longer version
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();
		assertEquals(category.getName(), "category name");
	}

	@Test
	public void shouldEstablishCategoryToReviewRelationships() {
		// one to many: Category to review
		// review is not the owner -- category is -- so make these reviews first

		Review java = reviewRepo.save(new Review("Java"));
		Review ruby = reviewRepo.save(new Review("Ruby"));

		Category category = new Category("OO Languages", "description", java, ruby);
		category = categoryRepo.save(category);
		long categoryId = category.getId();

		entityManager.flush();
		entityManager.clear();

		Optional<Category> result = categoryRepo.findById(categoryId);
		category = result.get();

		assertThat(category.getReviews(), containsInAnyOrder(java, ruby));
		// initially throws error --> unexpected iterable over...? need to "Generate
		// hashCode() and equals() in each entity

	}

	// query
	@Test
	public void shouldFindCategoryForReview() {
		Review java = reviewRepo.save(new Review("java"));
		// Review ruby = reviewRepo.save(new Review("Ruby"));

		Category ooLanguages = categoryRepo.save(new Category("OO Languages", "description", java));
		Category advancedJava = categoryRepo.save(new Category("Adv Java", "Description", java));
		// Category advancedRuby = categoryRepo.save(new Category("Adv Ruby",
		// "Description",
		// ruby));

		entityManager.flush();
		entityManager.clear();

		Collection<Category> categorysForReview = categoryRepo.findByReviewsContains(java);
		// tied to our Reviews Collection
		assertThat(categorysForReview, containsInAnyOrder(ooLanguages, advancedJava));
	}

	// query
	@Test
	public void shouldFindCategorysForReviewById() {
		Review ruby = reviewRepo.save(new Review("Ruby"));
		long reviewId = ruby.getId(); // access that specific review

		Category ooLanguages = categoryRepo.save(new Category("OO Languages", "description", ruby));
		Category advancedRuby = categoryRepo.save(new Category("Adv Ruby", "Description", ruby));

		entityManager.flush();
		entityManager.clear();

		Collection<Category> categorysForReview = categoryRepo.findByReviewsId(reviewId); // own query - not CRUD's
		assertThat(categorysForReview, containsInAnyOrder(ooLanguages, advancedRuby));

	}

}
