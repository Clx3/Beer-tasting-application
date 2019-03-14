package fi.tuni.tastingapp.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public User createUser(@RequestBody User user) throws UsernameAlreadyTakenException {
		User createdUser;
		
		try {
			createdUser = userRepo.save(user);
			
			return createdUser;
		} catch(DataIntegrityViolationException  e) {
			throw new UsernameAlreadyTakenException("Username is already in use!");			
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.CONFLICT)
	class UsernameAlreadyTakenException extends AuthenticationException {
		
		public UsernameAlreadyTakenException(String msg) {
			super(msg);
		}
		
	}

}
