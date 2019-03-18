package fi.tuni.tastingapp.security;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.tuni.tastingapp.security.Token;
import org.springframework.data.repository.Repository;



public interface TokenRepository {

    public List<Token> findAll();

    public boolean contains(String token);

    public String addNewToken();

}
