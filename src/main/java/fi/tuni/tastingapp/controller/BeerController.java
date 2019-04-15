package fi.tuni.tastingapp.controller;

import java.util.List;

import fi.tuni.tastingapp.security.TokenRepository;
import org.apache.tomcat.websocket.AuthenticationException;
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

	@Autowired
	private TokenRepository tokens;

	@RequestMapping(value = "beers/{beerIds}", method = RequestMethod.GET)
	public List<Beer> getBeersByIds(@PathVariable List<Long> beerIds) throws Exception{
		List<Beer> beers = beerRepository.findByIdIn(beerIds);
		if(beers.size() == 0){
			throw new BeerNotFoundException();
		}
		return beers;
	}

	@RequestMapping(value = "beers/", method = RequestMethod.GET)
	public List<Beer> getAllBeers() {
		return beerRepository.findAll();
	}

	@RequestMapping(value = "beers/add", method = RequestMethod.PUT)
	public Beer addBeer(@RequestBody Beer beer, @RequestHeader(value="Token") String token) throws JsonProcessingException, AuthenticationException {
		if(!(tokens.contains(token))){
			throw new AuthenticationException("unauthorized");
		}
		return beerRepository.save(beer);
	}

}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	class BeerNotFoundException extends Exception{

	public BeerNotFoundException() {
		super("No beers found");
	}
}