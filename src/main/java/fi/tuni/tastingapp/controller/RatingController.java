package fi.tuni.tastingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.tastingapp.entity.Rating;
import fi.tuni.tastingapp.repository.RatingRepository;

@RestController
public class RatingController {

	@Autowired
	private RatingRepository ratingRepository;
	
	@RequestMapping(value = "rating", method = RequestMethod.GET)
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}
	
	@RequestMapping(value = "rating", method = RequestMethod.POST)
	public Rating createOrUpdateRating(@RequestBody Rating rating) {
		Rating currentRating = ratingRepository.findByUserIdAndBeerId(rating.getUserId(), rating.getBeerId());
		
		if(currentRating != null) {
			currentRating.setRatingValue(rating.getRatingValue());
			currentRating.setComment(rating.getComment());
			return ratingRepository.save(currentRating);
		} else {
			return ratingRepository.save(rating);
		}
	}
	
}
