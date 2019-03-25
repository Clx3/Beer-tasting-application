package fi.tuni.tastingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beerAndTastingSession")
public class BeerAndTastingSession {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="beerId")
	private long beerId;
	
	@Column(name="tastingSessionId")
	private Long tastingSessionId;
	
	public BeerAndTastingSession() {}

	public BeerAndTastingSession(long beerId, Long tastingSessionId) {
		this.beerId = beerId;
		this.tastingSessionId = tastingSessionId;
	}

	public long getBeerId() {
		return beerId;
	}

	public void setBeerId(long beerId) {
		this.beerId = beerId;
	}

	public Long getTastingSessionId() {
		return tastingSessionId;
	}

	public void setTastingSessionId(Long tastingSessionId) {
		this.tastingSessionId = tastingSessionId;
	}

}
