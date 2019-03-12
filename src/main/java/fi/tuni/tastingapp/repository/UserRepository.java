package fi.tuni.tastingapp.repository;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
