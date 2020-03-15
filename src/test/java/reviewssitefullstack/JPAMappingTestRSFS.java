package reviewssitefullstack; // Mod 7 Graded Project

import static org.junit.Assert.*;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
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
import reviewssitefullstack.models.Comment;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.CommentRepository;
import reviewssitefullstack.repositories.ReviewRepository;
import reviewssitefullstack.repositories.TagRepository;

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

	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CommentRepository commentRepo;

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
		
		entityManager.flush();
		entityManager.clear();

		Collection<Category> categoriesForReview = categoryRepo.findByReviewsContains(java);
		// tied to our Reviews Collection
		assertThat(categoriesForReview, containsInAnyOrder(ooLanguages));
	}

	

	// ****************** TAGS *******************

	@Test
	public void shouldSaveAndLoadSingleTag() {
		Tag tag = tagRepo.save(new Tag("tag"));// pulls Review entity from review repository -->CRUD Repo
		// saves
		long tagId = tag.getId();

		entityManager.flush(); // forces JPA to hit the database when we try to find it
		entityManager.clear();

		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertEquals(tag.getName(), "tag");

	}
	
	@Test
	public void shouldAddAndLoadTagToReview() {
		Review lager = reviewRepo.save(new Review("lager"));
		
		Tag crisp = tagRepo.save(new Tag("crisp"));
		
		//adding to each other's lists
		lager.addTag(crisp);
		crisp.addReview(lager); // do same in populator
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Tag> tagsForReview = tagRepo.findByReviewsContains(lager);
		assertThat(tagsForReview, contains(crisp));
				
	}

	@Test
	public void shouldAddAndLoadReviewToTag() {
		Review lager = reviewRepo.save(new Review("lager"));
		
		Tag crisp = tagRepo.save(new Tag("crisp"));
		
		//adding to each other's lists
		lager.addTag(crisp);
		crisp.addReview(lager);
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Review> reviewForTag = reviewRepo.findByTagsContains(crisp);
		assertThat(reviewForTag, contains(lager));
				
	}
	
	// ****************** COMMENTS *******************

	@Test
	public void shouldSaveAndLoadSingleComment() {
		Comment comment = commentRepo.save(new Comment("comment", "comment description"));// pulls Review entity from review repository -->CRUD Repo
		// saves
		long commentId = comment.getId();

		entityManager.flush(); // forces JPA to hit the database when we try to find it
		entityManager.clear();

		Optional<Comment> result = commentRepo.findById(commentId);
		Comment commentFromDb = result.get();
		assertEquals(commentFromDb.getTitle(), "comment");

	}
	
	@Test
	public void shouldEstablishCommentToReviewRelationship() {
		// Review owns Comments --> make review (parent) first
		
		Review review = new Review("review title");
		reviewRepo.save(review);
		long reviewId = review.getId();
	
		Comment comment1 = new Comment("comment title 1", "content 1");
		Comment comment2 = new Comment("comment title 2", "content 2");
		
		
		review.addComment(comment1);
		review.addComment(comment2);
		commentRepo.save(comment1);
		commentRepo.save(comment2);
		
		entityManager.flush();
		entityManager.clear();

		//Many to One - many courses could use one book
		Optional<Review>result = reviewRepo.findById(reviewId);
		Review reviewFromDb = result.get();

		assertThat(reviewFromDb.getComments(), contains(comment1, comment2));
		
	}

	
	
	
}
