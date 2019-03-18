package fi.tuni.tastingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="beer")
public class Beer {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="beerName")
	private String beerName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="alcoholPercent")
	private float alcoholPercent;
	
	public Beer() {}
	
	public Beer(String beerName, String description, float alchoholAmount) {
		this.beerName = beerName;
		this.description = description;
		this.alcoholPercent = alchoholAmount;
	}

	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAlcoholPercent() {
		return alcoholPercent;
	}

	public void setAlcoholPercent(float alcoholPercent) {
		this.alcoholPercent = alcoholPercent;
	}

}
