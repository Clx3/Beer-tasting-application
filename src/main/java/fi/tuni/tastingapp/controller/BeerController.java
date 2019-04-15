package fi.tuni.tastingapp.controller;

import java.util.List;

import fi.tuni.tastingapp.entity.BeerAndTastingSession;
import fi.tuni.tastingapp.repository.BeerAndTastingSessionRepository;
import fi.tuni.tastingapp.repository.RatingRepository;
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

	@Autowired
	private BeerAndTastingSessionRepository beerAndTasting;

	@Autowired
	private RatingRepository ratingRepository;

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

	@RequestMapping(value = "beers/{beerId}", method = RequestMethod.DELETE)
	public void removeBeer(@PathVariable long beerId, @RequestHeader(value="Token") String token) throws AuthenticationException, BeerNotFoundException, BeerAlreadyInSession {
		if(!(tokens.contains(token))){
			throw new AuthenticationException("unauthorized");
		}
		if(beerRepository.findById(beerId).isPresent()){
			if(beerAndTasting.findAllByBeerId(beerId).isEmpty()){
				beerRepository.deleteById(beerId);
				ratingRepository.deleteAllByBeerId(beerId).forEach((rating -> System.out.println("Deleted rating: " + rating)));
			} else {
				throw new BeerAlreadyInSession();
			}
		} else {
			throw new BeerNotFoundException();
		}

	}

}
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	class BeerNotFoundException extends Exception{

	public BeerNotFoundException() {
		super("No beers found");
	}

}

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class BeerAlreadyInSession extends Exception {
	public BeerAlreadyInSession(){
		super("Can't delete, beer already added to session");
	}
}