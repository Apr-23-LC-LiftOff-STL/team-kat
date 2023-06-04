package org.launchcode.TasteBuddiesServer.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

@Entity
public class Event extends AbstractEntity {

    @NotNull
    private String entryCode;

    @NotNull
    private String location;

    @NotNull
    private String searchRadius;

    //defining a many-to-one relationship between two entities and specifying that the related entity should be fetched lazily to optimize performance.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_restaurants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> availableRestaurants = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<UserLikes> userLikedRestaurants = new ArrayList<>();

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date mealTime;

    public Event(){ }

    public Event(String entryCode, String location, String searchRadius, User initialUser, Date mealTime) {
        this.entryCode = entryCode;
        this.location = location;
        this.searchRadius = searchRadius;
        this.createdDate = new Date();
        this.users.add(initialUser);
        this.mealTime = mealTime;
    }

    public List<Restaurant> getAvailableRestaurants() {
        return availableRestaurants;
    }

    public void setAvailableRestaurants(List<Restaurant> availableRestaurants) {
        this.availableRestaurants = availableRestaurants;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UserLikes> getUserLikedRestaurants() {
        return userLikedRestaurants;
    }

    public void setUserLikedRestaurants(List<UserLikes> userLikedRestaurants) {
        this.userLikedRestaurants = userLikedRestaurants;
    }

    public Date getMealTime() {
        return mealTime;
    }

    public void setMealTime(Date mealTime) {
        this.mealTime = mealTime;
    }
}
