package org.launchcode.TasteBuddiesServer.models;


import javax.persistence.*;
import java.util.List;
@Entity
public class UserLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "userLikes", cascade = CascadeType.ALL)
    private User user;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_likes_restaurants",
            joinColumns = @JoinColumn(name = "user_likes_restaurant"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> likedRestaurants;

    public UserLikes(){

    }

    public UserLikes(User user, List<Restaurant> likedRestaurants){
        this.user = user;
        this.likedRestaurants = likedRestaurants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
