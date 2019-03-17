package fi.tuni.tastingapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beer")
public class Beer {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String description;
	
	private float alcoholAmount;
	
	public Beer() {}
	
	public Beer(String name, String description, float alchoholAmount) {
		this.name = name;
		this.description = description;
		this.alcoholAmount = alchoholAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAlcoholAmount() {
		return alcoholAmount;
	}

	public void setAlcoholAmount(float alcoholAmount) {
		this.alcoholAmount = alcoholAmount;
	}

}
