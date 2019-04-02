package fi.tuni.tastingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.UserAndTastingSession;

public interface UserAndTastingSessionRepository extends CrudRepository<UserAndTastingSession, Long> {
	
	public UserAndTastingSession save(UserAndTastingSession userAndTastingSession);
	
	public UserAndTastingSession findByUserIdAndTastingSessionId(long userId, long tastingSessionId);

}
