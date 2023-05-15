package org.launchcode.TasteBuddiesServer.models;

import java.util.ArrayList;
import java.util.List;

public class UserLikes extends AbstractEntity{
    private User user;
    private List<Restaurant> restaurants = new ArrayList<>();

    public UserLikes(User user, List<Restaurant> restaurants) {
        this.user = user;
        this.restaurants = restaurants;
    }
    public UserLikes(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
