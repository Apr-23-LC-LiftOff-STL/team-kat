package org.launchcode.TasteBuddiesServer.models.dto;

public class SearchDTO {
    private String term;

    public SearchDTO(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
