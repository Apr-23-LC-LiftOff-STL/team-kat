package org.launchcode.TasteBuddiesServer.models.dto;

import java.util.List;

public class CreateEventDTO {

    private List<Long> userId;
    private String location;
    private String searchRadius;


    public List<Long> getUserId() {
        return userId;
    }

    public void setUserId(List<Long> userId) {
        this.userId = userId;
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
