package fi.tuni.tastingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_and_tastingsession")
public class UserAndTastingSession {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "userId")
	private long userId;
	
	@Column(name = "tastingSessionId")
	private long tastingSessionId;
	
	public UserAndTastingSession() {}
	
	public UserAndTastingSession(long userId, long tastingSessionId) {
		this.userId = userId;
		this.tastingSessionId = tastingSessionId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getTastingSessionId() {
		return tastingSessionId;
	}

	public void setTastingSessionId(long tastingSessionId) {
		this.tastingSessionId = tastingSessionId;
	}
	
}
