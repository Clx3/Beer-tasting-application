package fi.tuni.tastingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.BeerAndTastingSession;

public interface BeerAndTastingSessionRepository extends CrudRepository<BeerAndTastingSession, Long> {

}
