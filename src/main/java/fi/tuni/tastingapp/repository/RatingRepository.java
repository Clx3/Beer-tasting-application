package fi.tuni.tastingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long> {
	
	public List<Rating> findAll();
	
	public Rating findByUserIdAndBeerId(long userId, long beerId);

	public List<Rating> findAllByBeerId(long beerId);

	public List<Rating> deleteAllByBeerId(long beerId);
}
