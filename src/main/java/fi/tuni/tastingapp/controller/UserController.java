package fi.tuni.tastingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.tastingapp.entity.User;
import fi.tuni.tastingapp.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
			
	@RequestMapping(value = "users/", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@RequestMapping(value = "users/", method = RequestMethod.PUT)
	public User createUser(@RequestBody User user) {
		User createdUser = userRepo.save(user);
		
		if(createdUser != null)
			return createdUser;
		else {
			/* IMPLEMENT */
			return null;
		}
	}

}
