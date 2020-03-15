package reviewssitefullstack.controllers;  //Mod 7 Graded Project 
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reviewssitefullstack.exceptions.ReviewNotFoundException;
import reviewssitefullstack.models.Review;
import reviewssitefullstack.repositories.CategoryRepository;
import reviewssitefullstack.repositories.ReviewRepository;




@Controller
public class ReviewController {
	
	@Resource
	CategoryRepository categoryRepo;
	@Resource
	ReviewRepository reviewRepo;
	
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
			//model.addAttribute("categories", categoryRepo.findByReviewsContains(review.get()));
			
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
	
	
	
	
}
