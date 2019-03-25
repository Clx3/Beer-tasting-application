package fi.tuni.tastingapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import fi.tuni.tastingapp.entity.Beer;
import fi.tuni.tastingapp.repository.BeerRepository;

@RestController
public class BeerController {

	@Autowired
	private BeerRepository beerRepository;

	@RequestMapping(value = "beers/{beerIds}", method = RequestMethod.GET)
	public List<Beer> getBeersByIds(@PathVariable List<Long> beerIds) {
		return beerRepository.findByIdIn(beerIds);
	}

	@RequestMapping(value = "beers/", method = RequestMethod.GET)
	public List<Beer> getAllBeers() {
		return beerRepository.findAll();
	}

	@RequestMapping(value = "beers/add", method = RequestMethod.PUT)
	public Beer addBeer(@RequestBody Beer beer) throws JsonProcessingException {
		return beerRepository.save(beer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Beer getBeerById(@PathVariable long id) throws BeerNotFoundException {
		Optional<Beer> beer = beerRepository.findById(id);
		if (beer.isPresent()) {
			return beer.get();
		} else {
			throw new BeerNotFoundException();
		}

	}
}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	class BeerNotFoundException extends Exception{

	public BeerNotFoundException() {
		super("Beer with requested id was not found from database");
	}
}