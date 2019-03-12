package fi.tuni.tastingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.tastingapp.entity.User;
import fi.tuni.tastingapp.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
			
	@RequestMapping("users/")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
