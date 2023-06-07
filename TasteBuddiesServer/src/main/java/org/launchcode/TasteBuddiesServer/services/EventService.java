package org.launchcode.TasteBuddiesServer.services;

import org.launchcode.TasteBuddiesServer.data.*;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.UserLikes;
import org.launchcode.TasteBuddiesServer.models.dto.CreateEventFormDTO;
import org.launchcode.TasteBuddiesServer.models.Event;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.UserLikesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserLikesRepository userLikesRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public static final char[] UPPERCASE_LETTERS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static final int ENTRY_CODE_LENGTH = 6;

    public Event createEvent(CreateEventFormDTO request, String userEmail) {
        Event event = new Event();
        event.setLocation(request.getLocation());
        event.setSearchRadius(request.getSearchRadius());

        String entryCode = generateUniqueEntryCode();
        event.setEntryCode(entryCode);

        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            event.getUsers().add(user);

            Event savedEvent = eventRepository.save(event);
            user.getEvents().add(savedEvent);
            userRepository.save(user);

            return savedEvent;
        } else {
            // handel the case when user is not found, throw exception or return default value
            return event;
        }

    }

    public String generateUniqueEntryCode() {
        //Implement unique entry

        String roomCode;
        StringBuilder codeBuilder = new StringBuilder();

        while (true) {

            /*
            This is a pretty simple approach to code generation, but there are approximately
            300 million possible combinations. So for now this is a fine way to generate codes.
            I think the design would eventually need to change so these codes expire.
             */
            for (int i = 0; i < ENTRY_CODE_LENGTH; i++) {
                int random = (int) Math.floor(Math.random() * 26);
                char letter = UPPERCASE_LETTERS[random];
                codeBuilder.append(letter);
            }

            roomCode = codeBuilder.toString();

            Optional possibleEvent = eventRepository.findByEntryCode(roomCode);

            if (possibleEvent.isEmpty()) {
                break;
            }

        }
        return roomCode;
    }

    public void saveLikedRestaurant(UserLikesDTO userLikesDTO) {
        // Retrieve the user, event, and restaurant IDs from the DTO
        Integer userId = userLikesDTO.getUserId();
        Integer eventId = userLikesDTO.getEventId();
        String restaurantId = userLikesDTO.getRestaurantId();

        // Retrieve the User and Event objects based on the provided IDs
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Fetch the UserLikes entity based on the provided user and event IDs
        Optional<UserLikes> userLikesOptional = userLikesRepository.findByUserAndEvent(user, event);
        UserLikes userLikes;

        if (userLikesOptional.isEmpty()) {
            userLikesOptional = this.createUserLikes(user, event);
        }

        // If UserLikes entity exists, update the liked restaurants
        userLikes = userLikesOptional.get();
        List<Restaurant> likedRestaurants = userLikes.getLikedRestaurants();
        // Check if the restaurant already exists in the likedRestaurant list
        boolean restaurantExists = likedRestaurants.stream()
                .anyMatch(restaurant -> restaurant.getId().equals(restaurantId));
        if (!restaurantExists) {
            // Create a new Restaurant object with the given restaurantId
            Restaurant restaurantToAdd = new Restaurant();
            restaurantToAdd.setId(restaurantId);

            // Add the restaurant to the liked Restaurants list
            if (userLikesDTO.isLike()) {
                userLikes.getLikedRestaurants().add(restaurantToAdd);
            } else {
                userLikes.getDislikedRestaurants().add(restaurantToAdd);
            }
            userLikesRepository.save(userLikes);
        }
    }

    public Optional<UserLikes> createUserLikes(User user, Event event) {
        UserLikes userLikes = new UserLikes(user, event);
        userLikesRepository.save(userLikes);
        return userLikesRepository.findByUserAndEvent(user, event);
    }

    public Event filterSeenEvents(Event event, User user) {

        List<Restaurant> restaurants = event.getAvailableRestaurants();

        UserLikes userLikes = event.getUserLikedRestaurants().stream()
                .filter(ul ->  ul.getUser().equals(user))
                .findFirst()
                .orElse(null);

        try {
            restaurants.removeIf(x -> userLikes.getLikedRestaurants().contains(x));
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        try {
            restaurants.removeIf(x -> userLikes.getDislikedRestaurants().contains(x));
        } catch (NullPointerException e) {
            System.out.println(e);
        }

        event.setAvailableRestaurants(restaurants);

        return event;

    }
}
