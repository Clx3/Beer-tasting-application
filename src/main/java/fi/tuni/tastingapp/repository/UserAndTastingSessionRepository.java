package fi.tuni.tastingapp.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.UserAndTastingSession;

public interface UserAndTastingSessionRepository extends CrudRepository<UserAndTastingSession, Long> {
	
	public UserAndTastingSession save(UserAndTastingSession userAndTastingSession);
	
	public UserAndTastingSession findByUserIdAndTastingSessionId(long userId, long tastingSessionId);
	
	@Query(value = "SELECT tasting_Session_Id FROM user_and_tastingsession WHERE user_Id LIKE(?1)", nativeQuery = true)
	public List<BigInteger> findTastingSessionIdsByUserId(long userId);

}
