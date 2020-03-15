package reviewssitefullstack.models; //Mod 7 Graded Project

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import reviewssitefullstack.models.Review;

@Entity
public class Category {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;

	@OneToMany(mappedBy = "category")
	private Collection<Review> reviews;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Collection<Review> getReviews() {
		return reviews;
	}

	public Category() {

	}

	public Category(String name, String description, Review... reviews) { // don't know how many ...= some collection
		this.name = name;
		this.description = description;
		this.reviews = new HashSet<>(Arrays.asList(reviews)); // brings in a collection of reviews
		// HasSet because we don't want duplicates, but it doesn't need to be in order
		
		for(Review review : reviews) {
			review.setCategory(this);
		}
	}

	// Source -> Generate hashCode() and equals()
	// JPA needs this so it knows how to assign the id --> must to for every entity

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
