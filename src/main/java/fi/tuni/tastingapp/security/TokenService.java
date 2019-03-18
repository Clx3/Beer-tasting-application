package fi.tuni.tastingapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TokenService implements TokenRepository {
    List <Token> tokens;

    public TokenService(){
        tokens = new ArrayList<Token>();
    }

    @Override
    public List<Token> findAll() {
        return null;
    }

    @Override
    public boolean contains(String a) {
        for(Token b : tokens){
            if(b.equals(a))
                return true;
        }
        return false;
    }

    @Override
    public String addNewToken() {
        Token newToken = new Token();
        newToken.setToken(getSaltString());
        tokens.add(newToken);
        return newToken.getToken();

    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


}
