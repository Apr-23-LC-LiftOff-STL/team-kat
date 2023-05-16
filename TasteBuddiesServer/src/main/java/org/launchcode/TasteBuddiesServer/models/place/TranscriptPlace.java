package org.launchcode.TasteBuddiesServer.models.place;

public class TranscriptPlace {
    private ResultsPlace results;

    public TranscriptPlace(ResultsPlace results) {
        this.results = results;
    }
    public TranscriptPlace(){}

    public ResultsPlace getResults() {
        return results;
    }

    public void setResults(ResultsPlace results) {
        this.results = results;
    }
}
