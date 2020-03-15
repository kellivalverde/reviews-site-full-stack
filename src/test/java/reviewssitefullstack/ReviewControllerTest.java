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
import reviewssitefullstack.exceptions.ReviewNotFoundException;
import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.ReviewRepository;

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
	

//	@Test
//	public void shouldAddSingleTagToReviewModel() throws TagNotFoundException {
//		List<Tag> tags = Arrays.asList(tag)
//	}
		
	}


