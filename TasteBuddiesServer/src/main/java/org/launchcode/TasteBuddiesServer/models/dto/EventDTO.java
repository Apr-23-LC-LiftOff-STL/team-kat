package org.launchcode.TasteBuddiesServer.models.dto;

public class EventDTO {
    private String location;
    private String searchRadius;

    public EventDTO(String location, String searchRadius) {
        this.location = location;
        this.searchRadius = searchRadius;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }
}
