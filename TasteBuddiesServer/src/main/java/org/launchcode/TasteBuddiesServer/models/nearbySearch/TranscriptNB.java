package org.launchcode.TasteBuddiesServer.models.nearbySearch;


import java.util.List;

public class TranscriptNB {
    private List<ResultsNB> results;
    private String next_page_token;

    public TranscriptNB(List<ResultsNB> results, String next_page_token) {
        this.results = results;
        this.next_page_token = next_page_token;
    }

    public TranscriptNB(){}

    public List<ResultsNB> getResults() {
        return results;
    }

    public void setResults(List<ResultsNB> results) {
        this.results = results;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }
}
