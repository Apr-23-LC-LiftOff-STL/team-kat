package org.launchcode.TasteBuddiesServer.models;

import java.util.ArrayList;
import java.util.List;

public class Event extends AbstractEntity {
    private String location;
    private String searchRadius;
    private String entryCode;
    private List<UserLikes> userLikedRestaurants = new ArrayList<>();

    public Event(String location, String searchRadius) {
        this.location = location;
        this.searchRadius = searchRadius;
    }
    public Event(){}

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

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public List<UserLikes> getUserLikedRestaurants() {
        return userLikedRestaurants;
    }

    public void setUserLikedRestaurants(List<UserLikes> userLikedRestaurants) {
        this.userLikedRestaurants = userLikedRestaurants;
    }
}
