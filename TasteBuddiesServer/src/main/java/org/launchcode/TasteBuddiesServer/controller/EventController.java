package org.launchcode.TasteBuddiesServer.controller;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;

import org.launchcode.TasteBuddiesServer.data.EventRepository;
import org.launchcode.TasteBuddiesServer.data.RestaurantRepository;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.Event;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.EventDTO;
import org.launchcode.TasteBuddiesServer.models.geocode.Location;
import org.launchcode.TasteBuddiesServer.models.place.ResultsPlace;
import org.launchcode.TasteBuddiesServer.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private NearbyService nearbyService;
    @Autowired
    private GeocodeService geocodeService;
    @Autowired
    private PlaceService placeService;

    @Value("${apiKey}")
    private String APIKey;

    @PostMapping("")
    public ResponseEntity<?> collectRestaurantData(
            @RequestBody EventDTO eventDTO,
            HttpServletRequest request
    )
            throws URISyntaxException, IOException, InterruptedException {


        Optional<User> possibleUser = userService.getUserFromRequest(request);
        if (possibleUser.isEmpty()) {
            return ResponseEntity.status(403).build();
        }

        Event newEvent = new Event(
                eventService.generateUniqueEntryCode(),
                eventDTO.getLocation(),
                eventDTO.getSearchRadius(),
                possibleUser.get()
        );

        Location location = geocodeService.getGeocodeFromAddress(eventDTO.getLocation());
        List<String> placeIDs = nearbyService.getNearbyIDsFromLocationAndSearchRadius(location, eventDTO.getSearchRadius());
        List<Restaurant> restaurants = new ArrayList<>();

        for (String placeID : placeIDs) {

            ResultsPlace resultsPlace = placeService.getRestaurantFromPlaceID(placeID);

            List<String> types = resultsPlace.getTypes();

            if(!restaurantRepository.existsById(placeID)) {
                if(!(types.contains("gas_station") || types.contains("convenience_store"))){
                    restaurantRepository
                            .save(new Restaurant(placeID, newEvent));
                    Optional<Restaurant> possibleRestaurant = restaurantRepository.findById(placeID);
                    restaurants.add(possibleRestaurant.get());
                }
            }
        }

        newEvent.setAvailableRestaurants(restaurants);
        eventRepository.save(newEvent);

        return ResponseEntity.status(200).build();
    }
}
