package fi.tuni.tastingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.BeerAndTastingSession;

public interface BeerAndTastingSessionRepository extends CrudRepository<BeerAndTastingSession, Long> {
	
	List<BeerAndTastingSession> findAllByTastingSessionId(long tastingSessionId);
	
	@Query(value = "SELECT beerId FROM beerAndTastingSession WHERE tastingSessionId LIKE(?1)", nativeQuery = true)
	Long[] findAllBeerIdsByTastingSessionId(long tastingSessionId);
}
