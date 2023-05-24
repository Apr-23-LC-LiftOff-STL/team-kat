package org.launchcode.TasteBuddiesServer.models.nearbySearch;


import java.util.List;

public class TranscriptNB {
    private List<ResultsNB> results;

    public TranscriptNB(List<ResultsNB> results) {
        this.results = results;
    }

    public TranscriptNB(){}

    public List<ResultsNB> getResults() {
        return results;
    }

    public void setResults(List<ResultsNB> results) {
        this.results = results;
    }
}
