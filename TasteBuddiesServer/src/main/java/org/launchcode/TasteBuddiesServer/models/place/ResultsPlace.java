package org.launchcode.TasteBuddiesServer.models.place;

public class ResultsPlace {
    private String place_id;
    private String name;
    private String formatted_address;

    public ResultsPlace(String place_id, String name, String formatted_address) {
        this.place_id = place_id;
        this.name = name;
        this.formatted_address = formatted_address;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}
