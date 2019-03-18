package fi.tuni.tastingapp.security;

public class Token {
    private String token;

    public Token(){

    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public boolean equals(String requested) {
        if(token.equals(requested))
            return true;

        return false;
    }

}
