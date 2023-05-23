package org.launchcode.TasteBuddiesServer.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    private String id;
    //The id field will store the place_id from the API call,
    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private int priceLevel;

    @NotNull
    private float rating;
    @ManyToMany(mappedBy = "availableRestaurants", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();

    // Add a list of users that favorited this restaurant
    @ManyToMany(mappedBy = "favoriteRestaurants")
    private List<User> favoritedByUsers = new ArrayList<>();


    public Restaurant(){

    }

    public Restaurant(String id, String name, String address, int priceLevel, float rating) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.priceLevel = priceLevel;
        this.rating = rating;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    //Add G/S


    public List<User> getFavoritedByUsers() {
        return favoritedByUsers;
    }

    public void setFavoritedByUsers(List<User> favoritedByUsers) {
        this.favoritedByUsers = favoritedByUsers;
    }
}
