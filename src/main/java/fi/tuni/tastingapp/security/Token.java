package fi.tuni.tastingapp.security;

public class Token {
    private String token;
    private long refreshed;

    public Token(){

    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
        refresh();
    }

    public boolean equals(String requested) {
        if(token.equals(requested))
            return true;

        return false;
    }

    public void refresh(){
        refreshed = System.currentTimeMillis();
    }

    public long getLatestRefresh(){
        return refreshed;
    }

}
