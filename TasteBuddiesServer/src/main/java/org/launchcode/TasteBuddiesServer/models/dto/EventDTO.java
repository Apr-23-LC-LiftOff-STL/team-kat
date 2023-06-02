package org.launchcode.TasteBuddiesServer.models.dto;

import org.launchcode.TasteBuddiesServer.models.AbstractEntity;
import org.launchcode.TasteBuddiesServer.models.Event;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.UserLikes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventDTO {
    private String entryCode;
    private String location;
    private String searchRadius;
    private List<Integer> userIDs;
    private List<Restaurant> restaurants;
    private List<UserLikes> userLikes;
    private Date mealTime;

    public EventDTO() {
    }

    public EventDTO(String entryCode,
                    String location,
                    String searchRadius,
                    List<Integer> userIDs,
                    List<Restaurant> restaurants,
                    List<UserLikes> userLikes,
                    Date mealTime
    ) {
        this.entryCode = entryCode;
        this.location = location;
        this.searchRadius = searchRadius;
        this.userIDs = userIDs;
        this.restaurants = restaurants;
        this.userLikes = userLikes;
        this.mealTime = mealTime;
    }

    public EventDTO(Event event) {
        this.entryCode = event.getEntryCode();
        this.location = event.getLocation();
        this.searchRadius = event.getSearchRadius();
        this.userIDs = event.getUsers()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toList());
        this.restaurants = event.getAvailableRestaurants();
        this.userLikes = event.getUserLikedRestaurants();
        this.mealTime = event.getMealTime();
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
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

    public List<Integer> getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(List<Integer> userIDs) {
        this.userIDs = userIDs;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<UserLikes> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<UserLikes> userLikes) {
        this.userLikes = userLikes;
    }

    public Date getMealTime() {
        return mealTime;
    }

    public void setMealTime(Date mealTime) {
        this.mealTime = mealTime;
    }
}

