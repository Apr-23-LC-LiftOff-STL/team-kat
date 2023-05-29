package org.launchcode.TasteBuddiesServer.models.nearbySearch;

import java.util.List;
public class ResultsNB {
    private String place_id;

    public ResultsNB(String place_id) {
        this.place_id = place_id;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }
}
