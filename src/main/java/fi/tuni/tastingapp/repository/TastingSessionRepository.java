package fi.tuni.tastingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.TastingSession;

public interface TastingSessionRepository extends CrudRepository<TastingSession, Long> {

	public List<TastingSession> findAll();
	
	public TastingSession findById(long id);
}
