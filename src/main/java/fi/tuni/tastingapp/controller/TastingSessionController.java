package fi.tuni.tastingapp.controller;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import fi.tuni.tastingapp.entity.BeerAndTastingSession;
import fi.tuni.tastingapp.entity.TastingSession;
import fi.tuni.tastingapp.repository.BeerAndTastingSessionRepository;
import fi.tuni.tastingapp.repository.TastingSessionRepository;

/**
 * 
 * TODO: Should we just create a service layer for our application?
 * This class looks like a fucking mess because there is too much
 * logic in the controllers. I think this is a good example why we should consider creating a
 * service layer..
 * 
 * @author Teemu
 *
 */
@RestController
public class TastingSessionController {
	
	private DateTimeFormatter dateTimeFormatter;
	
	private Gson gson;
	private JsonParser jsonParser;
	
	public TastingSessionController() {
		dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		gson = new Gson();
		jsonParser = new JsonParser();
	}
	
	@Autowired
	private TastingSessionRepository tastingSessionRepository;
	
	@Autowired
	private BeerAndTastingSessionRepository beerAndTastingSessionRepository;
	
	@Autowired
	@RequestMapping(value = "tastingsession/", method = RequestMethod.GET)
	public String getTastingSessions() {
		List<JsonObject> outputObjects = new ArrayList<>();
				
		List<TastingSession> tastingSessions = tastingSessionRepository.findAll();
		
		/* Whacky shit.. sigh */
		if(tastingSessions != null && tastingSessions.size() > 0) {
			for(TastingSession tastingSessionObj : tastingSessions) {
				JsonObject jsonObject = jsonParser.parse(gson.toJson(tastingSessionObj)).getAsJsonObject();
				int tastingSessionId = jsonObject.get("id").getAsInt();
				
				Long[] beerIds = beerAndTastingSessionRepository.findAllBeerIdsByTastingSessionId(tastingSessionId);
				
				jsonObject.addProperty("startingDate", dateTimeFormatter.format(tastingSessionObj.getStartingDate()));
				jsonObject.addProperty("beers", gson.toJson(beerIds));
				
				outputObjects.add(jsonObject);
			}
		}
		
		return gson.toJson(outputObjects);
	}
	
	@RequestMapping(value = "tastingsession/", method = RequestMethod.PUT)
	public TastingSession createTastingSession(@RequestBody String tastingSessionJsonString) {		
		JsonObject tastingSessionObject = jsonParser.parse(tastingSessionJsonString).getAsJsonObject();
		
		/* Whacky shit */
		TastingSession tastingSessionDTO = new TastingSession();
		tastingSessionDTO.setName(tastingSessionObject.get("name").getAsString());
		tastingSessionDTO.setStartingDate(LocalDateTime.parse(tastingSessionObject.get("startingDate").getAsString(), dateTimeFormatter));
		tastingSessionDTO.setAdditionalInfo(tastingSessionObject.get("additionalInfo").getAsString());
		
		
		return tastingSessionRepository.save(tastingSessionDTO);		
	}
	
	@RequestMapping(value = "tastingsession/addbeers/", method = RequestMethod.PUT)
	public List<BeerAndTastingSession> addBeersToTastingSession(@RequestBody String beersJsonArray) {
				
		JsonArray beersArray = jsonParser.parse(beersJsonArray).getAsJsonArray();
		if(beersArray != null && beersArray.size() > 0) {
			/* First we delete all previous beers found from this tastingSession: */
			
			/* Fuck me.. */
			int tastingSessionId = beersArray.get(0).getAsJsonObject().get("tastingSessionId").getAsInt();
			
			List<BeerAndTastingSession> toBeDeletedEntities = beerAndTastingSessionRepository.findAllByTastingSessionId(tastingSessionId);
			
			if(toBeDeletedEntities != null && toBeDeletedEntities.size() > 0)
				beerAndTastingSessionRepository.deleteAll(toBeDeletedEntities);
			
			/* Adding all beers from the new array */
			Type listType = new TypeToken<List<BeerAndTastingSession>>(){}.getType();
			List<BeerAndTastingSession> toBeAddedEntities = gson.fromJson(beersArray.toString(), listType);
			
			return (List<BeerAndTastingSession>) beerAndTastingSessionRepository.saveAll(toBeAddedEntities);
		}
			System.out.println(beersArray.toString());
		return null;
	}

}
