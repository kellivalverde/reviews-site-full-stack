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

import reviewssitefullstack.controllers.CategoryController;
import reviewssitefullstack.exceptions.CategoryNotFoundException;
import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.ReviewRepository;



public class CategoryControllerTest {

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
	private Model model;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldAddSingleCategoryToModel() throws CategoryNotFoundException {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));

		underTest.findOneCategory(arbitraryCategoryId, model);
		verify(model).addAttribute("categoryModel", category); // why plural?

	}

	@Test
	public void shouldAddAllCategoriesToModel() {
		Collection<Category> allCategories = Arrays.asList(category, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories); // methods from CRUD Repo

		underTest.findAllCategories(model);
		verify(model).addAttribute("categoriesModel", allCategories);
	}

}
