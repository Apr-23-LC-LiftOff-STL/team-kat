package org.launchcode.TasteBuddiesServer.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Entity
public class UserLikes extends AbstractEntity{
    private User user;
    @OneToMany
    @JoinColumn(name = "userLikes_Id")
    private List<Restaurant> likedRestaurants = new ArrayList<>();

    public UserLikes(User user, List<Restaurant> restaurants) {
        this.user = user;
        this.likedRestaurants = restaurants;
    }
    public UserLikes(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Restaurant> getRestaurants() {
        return likedRestaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.likedRestaurants = restaurants;
    }
}
