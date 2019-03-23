package fi.tuni.tastingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.TastingSession;

public interface TastingSessionRepository extends CrudRepository<TastingSession, Long> {

}
