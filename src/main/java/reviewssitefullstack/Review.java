package reviewssitefullstack; // Mod 7 Graded Project

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Review {

	@Id
	@GeneratedValue
	private long id; // primary key --> made by JPA
	private String title;
	private String imageUrl;
	private String content;
	private String date;

	@ManyToMany(mappedBy = "reviews") // because topics is not the owner
	private Collection<Category> categories;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	// default constructor required by JPA
	public Review() {

	}

	public Review(String name) {
		this.title = name;
	}

	public Review(long id, String title, String imageUrl, String content, String date, Category... categories) {
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		this.content = content;
		this.date = date;
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
		Review other = (Review) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
