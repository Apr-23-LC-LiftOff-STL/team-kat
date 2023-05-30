package org.launchcode.TasteBuddiesServer.models.dto;

public class TokenDTO {

    private String idToken;

    public TokenDTO(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
