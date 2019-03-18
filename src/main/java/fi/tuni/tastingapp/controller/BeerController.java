package fi.tuni.tastingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import fi.tuni.tastingapp.entity.Beer;
import fi.tuni.tastingapp.repository.BeerRepository;

@RestController
@RequestMapping("beers/")
public class BeerController {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Beer> getAllBeers() {
		return beerRepository.findAll();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.PUT)
	public Beer addBeer(@RequestBody Beer beer) throws JsonProcessingException {
		return beerRepository.save(beer);
	}

}
