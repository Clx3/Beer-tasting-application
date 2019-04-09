package fi.tuni.tastingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
