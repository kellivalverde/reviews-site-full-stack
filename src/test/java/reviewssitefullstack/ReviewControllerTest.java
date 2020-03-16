package reviewssitefullstack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import reviewssitefullstack.controllers.ReviewController;
import reviewssitefullstack.exceptions.CommentNotFoundException;
import reviewssitefullstack.exceptions.ReviewNotFoundException;
import reviewssitefullstack.exceptions.TagNotFoundException;
import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Comment;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.CommentRepository;
import reviewssitefullstack.repositories.ReviewRepository;
import reviewssitefullstack.repositories.TagRepository;

public class ReviewControllerTest {

	@InjectMocks // gives us access to our controller
	private ReviewController underTest;

	@Mock
	private Category category;
	@Mock
	private Category category2;
	@Mock
	private CategoryRepository categoryRepo;

	@Mock
	private Review review;
	@Mock
	private Review review2;

	@Mock
	private ReviewRepository reviewRepo;

	@Mock
	private TagRepository tagRepo;
	@Mock
	private Tag tag;
	
	@Mock
	private CommentRepository commentRepo;
	@Mock
	private Comment comment;
	

	@Mock
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleReviewToModel() throws ReviewNotFoundException {
		long arbitraryReviewId = 1;
		when(reviewRepo.findById(arbitraryReviewId)).thenReturn(Optional.of(review));

		underTest.findOneReview(arbitraryReviewId, model);
		verify(model).addAttribute("reviewModel", review); // why plural?

	}

	@Test
	public void shouldAddAllReviewsToModel() {
		Collection<Review> allReviews = Arrays.asList(review, review2);
		when(reviewRepo.findAll()).thenReturn(allReviews); // methods from CRUD Repo

		underTest.findAllReviews(model);
		verify(model).addAttribute("reviews", allReviews);
	}

	@Test
	public void shouldAddSingleTagToReviewModel() throws TagNotFoundException {

		Review review = new Review("review1");
		Tag tagToAdd = new Tag("tag1");

		Collection<Tag> allTags = Arrays.asList(tagToAdd);

		when(reviewRepo.findById(review.getId())).thenReturn(Optional.of(review));
		when(tagRepo.findByReviewsContains(review)).thenReturn(allTags);

		underTest.addTag(review.getId(), tagToAdd.getName(), model);
		verify(model).addAttribute("tagsModel", tagRepo.findByReviewsContains(review));

	}

//	
//	@Test
//	public void shouldAddSingleCommentToReviewModel() throws CommentNotFoundException {
//
//		Review review = new Review("review1");
//		Comment commentToAdd = new Comment("comment1", "comment content");
//
//		Collection<Comment> allComments = Arrays.asList(commentToAdd);
//
//		when(reviewRepo.findById(review.getId())).thenReturn(Optional.of(review));
//		when(commentRepo.findByReviewsContains(review)).thenReturn(allComments);
//
//		underTest.addComment(review.getId(), commentToAdd.getTitle(), commentToAdd.getContent(), model);
//		verify(model).addAttribute("commentsModel", commentRepo.findByReviewsContains(review));
//
//	}
	
}



