package org.launchcode.TasteBuddiesServer.models.geocode;

import java.util.List;

public class TranscriptGC {
    private List<TranscriptGC> results;

    public TranscriptGC(List<TranscriptGC> results) {
        this.results = results;
    }

    public List<TranscriptGC> getResults() {
        return results;
    }

    public void setResults(List<TranscriptGC> results) {
        this.results = results;
    }
}
