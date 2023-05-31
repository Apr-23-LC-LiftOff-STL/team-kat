package org.launchcode.TasteBuddiesServer.models.place;

public class TranscriptPlace {
    private ResultsPlace result;

    public TranscriptPlace(ResultsPlace result) {
        this.result = result;
    }
    public TranscriptPlace(){}

    public ResultsPlace getResult() {
        return result;
    }

    public void setResult(ResultsPlace results) {
        this.result = result;
    }
}
