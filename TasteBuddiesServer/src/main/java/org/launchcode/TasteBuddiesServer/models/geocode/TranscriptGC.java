package org.launchcode.TasteBuddiesServer.models.geocode;

import java.util.List;

public class TranscriptGC {
    private List<ResultsGC> results;

    public TranscriptGC(List<ResultsGC> results) {
        this.results = results;
    }
    public TranscriptGC(){}

    public List<ResultsGC> getResults() {
        return results;
    }

    public void setResults(List<ResultsGC> results) {
        this.results = results;
    }
}
