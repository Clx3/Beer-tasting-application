package fi.tuni.tastingapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tastingSession")
public class TastingSession {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="startingDate")
	private LocalDateTime startingDate;
	
	@Column(name="additionalInfo")
	private String additionalInfo;
	
	public TastingSession() {}
		
	public TastingSession(String name, LocalDateTime startingDate, String additionalInfo) {
		this.name = name;
		this.startingDate = startingDate;
		this.additionalInfo = additionalInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDateTime startingDate) {
		this.startingDate = startingDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

}
