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
import reviewssitefullstack.controllers.TagController;
import reviewssitefullstack.exceptions.CategoryNotFoundException;
import reviewssitefullstack.exceptions.TagNotFoundException;
import reviewssitefullstack.models.Category;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.ReviewRepository;
import reviewssitefullstack.repositories.TagRepository;


public class TagControllerTest {
	@InjectMocks // gives us access to our controller
	private TagController underTest;

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
	public void shouldAddSingleTagToModel() throws TagNotFoundException {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));

		underTest.findOneTag(arbitraryTagId, model);
		verify(model).addAttribute("tagModel", tag); 
	}

	@Test
	public void shouldAddAllTagsToModel() {
		Collection<Tag> allTags = Arrays.asList(tag, tag2);
		when(tagRepo.findAll()).thenReturn(allTags); // methods from CRUD Repo

		underTest.findAllTags(model);
		verify(model).addAttribute("tagsModel", allTags);
	}

}
