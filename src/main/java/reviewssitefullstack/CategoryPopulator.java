package reviewssitefullstack;
//Mod 7 Graded Project 
import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;

	@Resource
	private ReviewRepository reviewRepo;

	@Override
	public void run(String... args) throws Exception {
		// data
		
		Review ipaBeer = new Review("IPA Beer","/images/green-beer.jpg", "Content goes here", "Date");
		ipaBeer = reviewRepo.save(ipaBeer);
		
		Review spring = new Review("Spring");
		spring = reviewRepo.save(spring);
		
		Review tdd = new Review("TDD");
		tdd = reviewRepo.save(tdd);
		
		
		
		Category ipa = new Category("IPAs", "Strong and hop-forward", ipaBeer);
		ipa = categoryRepo.save(ipa);
		
		Category java102 = new Category("Advanced Java", "Learn how to test a JPA app", spring, tdd);
		java102 = categoryRepo.save(java102);
		
		tdd = reviewRepo.save(tdd);
		spring = reviewRepo.save(spring);
		ipaBeer = reviewRepo.save(ipaBeer);		
	}	
}
