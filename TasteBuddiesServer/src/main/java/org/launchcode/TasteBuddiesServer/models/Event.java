package org.launchcode.TasteBuddiesServer.models;

import org.launchcode.TasteBuddiesServer.service.EventService;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {
    private String location;
    private String searchRadius;
    private String entryCode;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<UserLikes> userLikedRestaurants = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<Restaurant> availableRestaurants = new ArrayList<>();

    public Event(String location, String searchRadius, String entryCode) {
        this.location = location;
        this.searchRadius = searchRadius;
        this.entryCode = entryCode;
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

    public List<Restaurant> getAvailableRestaurants() {
        return availableRestaurants;
    }

    public void setAvailableRestaurants(List<Restaurant> availableRestaurants) {
        this.availableRestaurants = availableRestaurants;
    }
}
