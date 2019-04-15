package fi.tuni.tastingapp.controller;

import java.util.List;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
	
	@RequestMapping(value = "users/login", method = RequestMethod.POST)
	public User login(@RequestBody User user) throws UserAuthenticationException {
		if(user != null) {
			User userFromDatabase = userRepo.findByUsername(user.getUsername());
			
			if(userFromDatabase == null)
				throw new UserAuthenticationException("User " + user.getUsername() + " not found.");
			
			/* EBIN :-D */
			if(user.getPassword().contentEquals(userFromDatabase.getPassword()))
				return userFromDatabase;
			else
				throw new UserAuthenticationException("Invalid password!");
		}
		return null;
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable long id) {

		return userRepo.findById(id);
	}

	@ResponseStatus(code = HttpStatus.CONFLICT)
	class UsernameAlreadyTakenException extends AuthenticationException {
		
		public UsernameAlreadyTakenException(String msg) {
			super(msg);
		}
		
	}
	
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	class UserAuthenticationException extends AuthenticationException {
		
		public UserAuthenticationException(String msg) {
			super(msg);
		}
		
	}



}
