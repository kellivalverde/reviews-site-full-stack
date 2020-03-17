
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

import reviewssitefullstack.controllers.CategoryController;
import reviewssitefullstack.exceptions.CategoryNotFoundException;
import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.ReviewRepository;
import reviewssitefullstack.repositories.TagRepository;


public class TagControllerTest {
	@InjectMocks // gives us access to our controller
	private CategoryController underTest;

	// mocking populator
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
	private Tag tag;
	@Mock
	private Tag tag2;
	@Mock
	private TagRepository tagRepo;
	
	@Mock
	private Model model;

	
	
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
