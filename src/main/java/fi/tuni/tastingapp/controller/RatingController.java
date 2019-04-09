package fi.tuni.tastingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.tastingapp.repository.RatingRepository;

@RestController
public class RatingController {

	@Autowired
	private RatingRepository ratingRepository;
	
}
