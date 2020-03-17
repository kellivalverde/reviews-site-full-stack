package reviewssitefullstack.controllers; //Mod 7 Graded Project 

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import reviewssitefullstack.exceptions.ReviewNotFoundException;
import reviewssitefullstack.models.Comment;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.models.Tag;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.CommentRepository;
import reviewssitefullstack.repositories.ReviewRepository;
import reviewssitefullstack.repositories.TagRepository;

@Controller
public class ReviewController {

	@Resource
	CategoryRepository categoryRepo;
	@Resource
	ReviewRepository reviewRepo;
	@Resource
	TagRepository tagRepo;
	@Resource
	CommentRepository commentRepo;

// replaced by Category page
//	@RequestMapping("/reviews") // end-point - > route must match html
//	public String findAllReviews(Model model) {
//		model.addAttribute("reviewsModel", reviewRepo.findAll());
//		return ("reviews-template");
//	}

	@RequestMapping("/review/{id}")
	public String findOneReview(@PathVariable(value = "id") long id, Model model) throws ReviewNotFoundException {
		Optional<Review> review = reviewRepo.findById(id);

		if (review.isPresent()) {
			model.addAttribute("reviewModel", review.get()); // why plural?
			model.addAttribute("categoryModel", review.get().getCategory());
			
			// model.addAttribute("categories",
			// categoryRepo.findByReviewsContains(review.get()));

			return "review-template"; // template for single review
		}
		throw new ReviewNotFoundException();
	}

	@RequestMapping("/reviews")
	public String findAllReviews(Model model) {
		Iterable<Review> reviews = reviewRepo.findAll();
		model.addAttribute("reviews", reviews);

		return "reviews";
	}

	@RequestMapping(path = "/review/{id}/tag", method = RequestMethod.POST)
	public String addTag(@PathVariable(value = "id") long id, String tagName, Model model) {
		Optional<Review> review = reviewRepo.findById(id);

		Tag tagToAdd = tagRepo.findByNameIgnoreCaseLike(tagName);

		if (tagToAdd == null) {
			tagToAdd = new Tag(tagName);
			tagRepo.save(tagToAdd);
		}

		review.get().addTag(tagToAdd);
		tagToAdd.addReview(review.get());

		reviewRepo.save(review.get());
		tagRepo.save(tagToAdd);

		model.addAttribute("tagsModel", tagRepo.findByReviewsContains(review.get()));

		return "partials/tags-list-added";

	}
	
	//get and show all tags and reviews associated --> in my TagsController
	
	
	

	@RequestMapping(path="/review/{id}/comment", method=RequestMethod.POST)  
	public String addComment(@PathVariable(value ="id")long id, String title, String content, Model model) {
		Optional<Review> review = reviewRepo.findById(id);
		
		Comment commentToAdd = new Comment();
		
		review.get().addComment(commentToAdd);
		
		reviewRepo.save(review.get());
		commentRepo.save(commentToAdd);
		
		model.addAttribute("commentsModel", commentRepo.findByReviewContains(review.get()));
	
	
		return "redirect:/review/{id}";
	}

}
