package org.launchcode.TasteBuddiesServer.controller.api;

import org.launchcode.TasteBuddiesServer.config.JwtUtil;
import org.launchcode.TasteBuddiesServer.controller.Services.UserService;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true")
public class APIUserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers() {
        List<User> possibleUsers = (List<User>) userRepository.findAll();

        if (possibleUsers.size() == 0) {
            return ResponseEntity.status(204).body(null);
        }

        List<UserDTO> users = possibleUsers
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("")
    public ResponseEntity<?> getLoggedInUser(
            HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION).substring(7);
        String email = jwtUtil.extractUsername(token);

        Optional<User> possibleUser = userRepository.findByEmail(email);
        if (possibleUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        UserDTO user = new UserDTO(possibleUser.get());

        return ResponseEntity.status(200).body(user);
    }


    //add endpoints to add and remove favorite restaurants
    @PostMapping("/{userId}/favorites/{restaurantId}")
    public ResponseEntity<?> addFavoriteRestaurant(@PathVariable Integer userId, @PathVariable String restaurantId) {
        try {
            userService.addFavoriteRestaurant(userId, restaurantId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{userId}/favorites/{restaurantId}")
    public ResponseEntity<?> removeFavoriteRestaurant(@PathVariable Integer userId, @PathVariable String restaurantId) {
        try {
            userService.removeFavoriteRestaurant(userId, restaurantId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<?> getFavoriteRestaurants(@PathVariable Integer userId) {
        try {
            List<Restaurant> favoriteRestaurants = userService.getFavoriteRestaurants(userId);
            if (favoriteRestaurants == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(favoriteRestaurants);
            }
        } catch (Exception e) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
