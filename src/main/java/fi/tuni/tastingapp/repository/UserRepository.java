package fi.tuni.tastingapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findAll();
	
	public User findByUsername(String username);

	public User findById(long id);

}
