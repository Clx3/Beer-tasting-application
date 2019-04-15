package fi.tuni.tastingapp.controller;

import java.util.List;

import fi.tuni.tastingapp.security.TokenRepository;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fi.tuni.tastingapp.entity.Rating;
import fi.tuni.tastingapp.repository.RatingRepository;

@RestController
public class RatingController {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private TokenRepository tokens;
	
	@RequestMapping(value = "rating", method = RequestMethod.GET)
	public List<Rating> getAll() {
		return ratingRepository.findAll();
	}
	
	@RequestMapping(value = "rating", params = {"userId", "beerId"}, method = RequestMethod.GET)
	public Rating getByUserIdAndBeerId(
			@RequestParam long userId,
			@RequestParam long beerId) {

		return ratingRepository.findByUserIdAndBeerId(userId, beerId);
	}

	@RequestMapping(value = "rating/{beerId}")
	public List<Rating> getRatingsByBeerId(@PathVariable long beerId){
		return ratingRepository.findAllByBeerId(beerId);
	}

	
	@RequestMapping(value = "rating", method = RequestMethod.POST)
	public Rating createOrUpdateRating(@RequestBody Rating rating, @RequestHeader(value="Token") String token) throws AuthenticationException {
		Rating currentRating = ratingRepository.findByUserIdAndBeerId(rating.getUserId(), rating.getBeerId());

		if(!(tokens.contains(token))){
			throw new AuthenticationException("unauthorized");
		}
		
		if(currentRating != null) {
			currentRating.setRatingValue(rating.getRatingValue());
			currentRating.setComment(rating.getComment());
			return ratingRepository.save(currentRating);
		} else {
			return ratingRepository.save(rating);
		}
	}
	
}
