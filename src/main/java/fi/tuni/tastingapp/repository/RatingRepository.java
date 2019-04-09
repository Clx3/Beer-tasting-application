package fi.tuni.tastingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long> {
	
	public List<Rating> findAll();

}
