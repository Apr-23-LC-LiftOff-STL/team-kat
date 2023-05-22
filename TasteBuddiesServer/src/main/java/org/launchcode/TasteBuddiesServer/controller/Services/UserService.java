package org.launchcode.TasteBuddiesServer.controller.Services;


import org.launchcode.TasteBuddiesServer.data.RestaurantRepository;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    public void addFavoriteRestaurant(Integer userId, String restaurantId) {
        // Get the user ".orElseThrow" calls on 'Optional<User>
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        // Get the restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found."));


            // Add the restaurant to the user's favorites
            user.getFavoriteRestaurants().add(restaurant);
            // Save the user
            userRepository.save(user);
    }

    public void removeFavoriteRestaurant(Integer userId, String restaurantId) {

        // Get the user ".orElseThrow" calls on 'Optional<User>
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
        // Get the restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found."));


        // Add the restaurant to the user's favorites
        user.getFavoriteRestaurants().add(restaurant);
        // Save the user
        userRepository.save(user);
    }

    public List<Restaurant> getFavoriteRestaurants(Integer userId) {
        // Get the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found."));
            return user.getFavoriteRestaurants();

    }
}

