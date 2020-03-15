package reviewssitefullstack.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	private long id; // primary key --> made by JPA
	private String name;
	
	
	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	// default constructor required by JPA
	public Tag() {

	}

	public Tag(long id, String name) {
		this.id = id;
		this.name = name;
		
	}
	
}
