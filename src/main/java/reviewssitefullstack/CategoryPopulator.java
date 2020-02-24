package reviewssitefullstack;

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
		
		Review java = new Review("Java");
		java = reviewRepo.save(java);
		
		Review spring = new Review("Spring");
		spring = reviewRepo.save(spring);
		
		Review tdd = new Review("TDD");
		tdd = reviewRepo.save(tdd);
		
		
		
		Category java101 = new Category("Intro to Java", "Learn the fundamentals of Java Programming", java);
		java101 = categoryRepo.save(java101);
		
		Category java102 = new Category("Advanced Java", "Learn how to test a JPA app", java, tdd);
		java102 = categoryRepo.save(java102);
		
	}	
}
