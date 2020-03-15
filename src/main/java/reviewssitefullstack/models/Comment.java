package reviewssitefullstack.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Comment {
	
		@Id
		@GeneratedValue
		private long id;
		private String title;
		private String content;

		@JsonIgnore
		@ManyToOne
		private Review review;
		

		public long getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public String getContent() {
			return content;
		}
		
		public void setReview(Review review) {
			this.review = review;
					
		}

		
		public Comment() {

		}

		public Comment(String title, String content) {
			this.title = title;
			this.content = content;
			
//			this.review = review;

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((content == null) ? 0 : content.hashCode());
			result = prime * result + (int) (id ^ (id >>> 32));
			result = prime * result + ((review == null) ? 0 : review.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
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
			Comment other = (Comment) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			if (id != other.id)
				return false;
			if (review == null) {
				if (other.review != null)
					return false;
			} else if (!review.equals(other.review))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}

		
}
