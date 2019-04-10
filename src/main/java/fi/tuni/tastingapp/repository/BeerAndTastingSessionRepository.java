package fi.tuni.tastingapp.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.BeerAndTastingSession;

public interface BeerAndTastingSessionRepository extends CrudRepository<BeerAndTastingSession, Long> {
	
	List<BeerAndTastingSession> findAllByTastingSessionId(long tastingSessionId);
	
	@Query(value = "SELECT beer_Id FROM beer_And_Tasting_Session WHERE tasting_Session_Id LIKE(?1)", nativeQuery = true)
	List<BigInteger> findAllBeerIdsByTastingSessionId(Long tastingSessionId);
}
