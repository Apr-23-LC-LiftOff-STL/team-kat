package org.launchcode.TasteBuddiesServer.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class UserLikes extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_likes_restaurants",
            joinColumns = @JoinColumn(name = "user_likes_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> likedRestaurants;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_dislikes_restaurants",
            joinColumns = @JoinColumn(name = "user_dislikes_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> dislikedRestaurants;

    public UserLikes(){ }

    public UserLikes(User user, List<Restaurant> likedRestaurants){
        this.user = user;
        this.likedRestaurants = likedRestaurants;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Restaurant> getLikedRestaurants() {
        return likedRestaurants;
    }

    public void setLikedRestaurants(List<Restaurant> likedRestaurants) {
        this.likedRestaurants = likedRestaurants;
    }

    public List<Restaurant> getRestaurants() {
        return likedRestaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.likedRestaurants = restaurants;
    }
}
