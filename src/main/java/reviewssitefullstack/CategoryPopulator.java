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
		
		
		//each Review only has one category
		Review ipaBeer = new Review("Revolution Anti-Hero IPA","/images/revolution-anti-hero.jpg", "Anti-Hero IPA Content goes here", "5/11/2019");
		ipaBeer = reviewRepo.save(ipaBeer);
		
		Review stoutBeer = new Review("VooDoo Brewery: BBVDD Imperial Stout","/images/VooDoo-BBVD-beer.jpg", "BBVDD Imperial Stout Content goes here", "12/7/2019");
		stoutBeer = reviewRepo.save(stoutBeer);
		
		Review spaceMothBeer = new Review("Space Moth","/images/space-moth.jpg", "Space Moth Content goes here", "12/7/2019");
		spaceMothBeer = reviewRepo.save(spaceMothBeer);
				
		Review wheatBeer = new Review("Lamplighter Group Theory Wheat Beer","/images/lamplighter-wheatbeer.jpg", "Group Theory Content goes here", "9/1/2019" );
		wheatBeer = reviewRepo.save(wheatBeer);
		
		
		//Categories can have multiple reviews
		Category ipaCat = new Category("IPA Category", "Strong and hop-forward", ipaBeer);
		ipaCat = categoryRepo.save(ipaCat);
		
		Category stoutCat = new Category("Stout Category", "Robust, smokey and sweet", stoutBeer, spaceMothBeer);
		stoutCat = categoryRepo.save(stoutCat);
		
		Category wheatCat = new Category("Wheat Beer Category", "Hazy and Light", wheatBeer);
		wheatCat = categoryRepo.save(wheatCat);
		
		reviewRepo.save(spaceMothBeer);
		reviewRepo.save(wheatBeer);
		reviewRepo.save(stoutBeer);
		reviewRepo.save(ipaBeer);		
	}	
}
