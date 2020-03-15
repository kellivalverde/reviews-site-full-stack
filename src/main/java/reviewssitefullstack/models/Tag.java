package reviewssitefullstack.models;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private long id; // primary key --> made by JPA
	private String name;
	
	
	@ManyToMany(mappedBy = "tags")
	private Collection<Review> reviews = new ArrayList<Review>();
	
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Collection<Review> getReviews(){
		return reviews;
	}
	
	
	
	
	// default constructor required by JPA
	public Tag() {

	}

	public Tag(String name) {
		this.name = name;
		
	}
	

	public void addReview(Review review) {
		reviews.add(review);
		}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
}
