package reviewssitefullstack;

import java.util.Optional;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

	@Resource
	CategoryRepository categoryRepo;

	@RequestMapping("/category")
	public String findOneCategory(@RequestParam(value = "id") long id, Model model) throws CategoryNotFoundException {
		Optional<Category> category = categoryRepo.findById(id);
		// have to handle if it is or is not present

		if (category.isPresent()) {
			model.addAttribute("categoryModel", category.get()); // why plural?
			return "category"; // template for single category
		}
		throw new CategoryNotFoundException();
	}

	@RequestMapping("/categories") // end-point
	public String findAllCategories(Model model) {
		model.addAttribute("categoriesModel", categoryRepo.findAll()); // model we are referencing
		return "categories"; // template -> does not have to match the end-point
	}

}
