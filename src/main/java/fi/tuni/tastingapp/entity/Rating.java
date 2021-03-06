package fi.tuni.tastingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "beerId")
	private long beerId;
	
	@Column(name = "ratingValue")
	private double ratingValue;
	
	@Column(name = "comment")
	private String comment;
	
	public Rating() {}

	public Rating(long userId, long beerId, float ratingValue, String comment) {
		this.userId = userId;
		this.beerId = beerId;
		this.ratingValue = ratingValue;
		this.comment = comment;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBeerId() {
		return beerId;
	}

	public void setBeerId(long beerId) {
		this.beerId = beerId;
	}

	public double getRatingValue() {
		return ratingValue;
	}

	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
