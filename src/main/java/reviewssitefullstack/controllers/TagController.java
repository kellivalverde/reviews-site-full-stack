package reviewssitefullstack.controllers; //Mod 7 Graded Project 

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import reviewssitefullstack.exceptions.CategoryNotFoundException;
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

@Controller
public class TagController {
	@Resource
	CategoryRepository categoryRepo;
	@Resource
	ReviewRepository reviewRepo;
	@Resource
	TagRepository tagRepo;
	
	
	@RequestMapping("/tags")
	public String findAllTags(Model model) {
		Iterable<Tag> tags = tagRepo.findAll();
		model.addAttribute("tagsModel", tags); //model for Thymeleaf

		return "tags-template"; //html file name
	}


	
	@RequestMapping("/tag/{id}")
	public String findOneTag(@PathVariable(value = "id") long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);
		// have to handle if it is or is not present

		if (tag.isPresent()) {
			model.addAttribute("tagModel", tag.get()); // why plural?
			return "single-tag-template"; // template for single category
		}
		throw new TagNotFoundException();
	}
}
