package reviewssitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;




@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerMockMVCTest {


	@Resource
	private MockMvc mvc;

	@Mock
	private Category category;
	@Mock
	private Category category2;
	@MockBean
	private CategoryRepository categoryRepo;

	@Mock
	private Review review;
	@Mock
	private Review review2;
	@MockBean
	private ReviewRepository reviewRepo;
	
	@Test
	public void shouldRouteToSingleCategoryView() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(view().name(is("category")));
	}

	@Test
	public void shouldBeOkForSingleCategory() throws Exception {
		long arbitraryCategoryId = 1;
		when(categoryRepo.findById(arbitraryCategoryId)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(status().isOk());
	}

	@Test
	public void shouldNotBeOkForSingleCategory() throws Exception {
		mvc.perform(get("/category?id=1")).andExpect(status().isNotFound());
	}

	@Test
	public void shouldPutSingleCategoryIntoModel() throws Exception {
		when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
		mvc.perform(get("/category?id=1")).andExpect(model().attribute("categoryModel", is(category)));
	}

	@Test
	public void shouldRouteToAllCategoryView() throws Exception {
		mvc.perform(get("/categories")).andExpect(view().name(is("categories"))); // hits our template
	} // was "/show-categories" in previous demo

	@Test
	public void shouldBeOkForAllCategories() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
		}

	@Test
	public void shouldPutAllCategoriesIntoModel() throws Exception {
		Collection<Category> allCategories = Arrays.asList(category, category2);
		when(categoryRepo.findAll()).thenReturn(allCategories);
		
		mvc.perform(get("/categories")).andExpect(model().attribute("categoriesModel", is(allCategories)));
	}
	
}
