package org.launchcode.TasteBuddiesServer.controllers;

import org.launchcode.TasteBuddiesServer.data.EventRepository;
import org.launchcode.TasteBuddiesServer.data.RestaurantRepository;
import org.launchcode.TasteBuddiesServer.exception.EventDoesNotExistException;
import org.launchcode.TasteBuddiesServer.exception.RoomCodeDoesNotExistException;
import org.launchcode.TasteBuddiesServer.exception.UserAlreadyJoinedEventException;
import org.launchcode.TasteBuddiesServer.models.Event;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.CreateEventFormDTO;
import org.launchcode.TasteBuddiesServer.models.dto.EventDTO;
import org.launchcode.TasteBuddiesServer.models.dto.JoinEventDTO;
import org.launchcode.TasteBuddiesServer.models.dto.UserLikesDTO;
import org.launchcode.TasteBuddiesServer.models.geocode.Location;
import org.launchcode.TasteBuddiesServer.models.place.ResultsPlace;
import org.launchcode.TasteBuddiesServer.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true"
)
public class EventController {
    private final RestaurantRepository restaurantRepository;
    private final EventRepository eventRepository;
    private final UserService userService;
    private final EventService eventService;
    private final NearbyService nearbyService;
    private final GeocodeService geocodeService;
    private final PlaceService placeService;

    public EventController(RestaurantRepository restaurantRepository, EventRepository eventRepository, UserService userService, EventService eventService, NearbyService nearbyService, GeocodeService geocodeService, PlaceService placeService) {
        this.restaurantRepository = restaurantRepository;
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.eventService = eventService;
        this.nearbyService = nearbyService;
        this.geocodeService = geocodeService;
        this.placeService = placeService;
    }

    @Value("${apiKey}")
    private String APIKey;

    @PostMapping("")
    public ResponseEntity<?> getEventFromId(
            @RequestBody int eventId,
            HttpServletRequest request
    ) {
        Event event = eventService.getEventFromId(eventId);
        User currentUser = userService.getUserFromRequest(request);
        Event filteredEvent = eventService.filterSeenEvents(event, currentUser);
        return ResponseEntity.status(200).body(new EventDTO(filteredEvent, currentUser));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllEvents(HttpServletRequest request) {

        User currentUser = userService.getUserFromRequest(request);
        List<Event> events = eventRepository.findAllByUsers(currentUser);

        if (events.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        List<EventDTO> allEvents = ((List<Event>) events)
                .stream()
                .map(event -> {
                    return new EventDTO(event, currentUser);
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(200).body(allEvents);
    }

    @PostMapping("create")
    public ResponseEntity<?> collectRestaurantData(
            @RequestBody CreateEventFormDTO createEventFormDTO,
            HttpServletRequest request
    )
            throws URISyntaxException, IOException, InterruptedException {


        User currentUser = userService.getUserFromRequest(request);
        Event newEvent = new Event(
                eventService.generateUniqueEntryCode(),
                createEventFormDTO.getLocation(),
                createEventFormDTO.getSearchRadius(),
                currentUser,
                createEventFormDTO.getMealTime()
        );

        Location location = geocodeService.getGeocodeFromAddress(createEventFormDTO.getLocation());
        List<String> placeIDs = nearbyService.getNearbyIDsFromLocationAndSearchRadius(location, createEventFormDTO.getSearchRadius());
        List<Restaurant> restaurants = new ArrayList<>();

        for (String placeID : placeIDs) {

            ResultsPlace resultsPlace = placeService.getRestaurantFromPlaceID(placeID);

            List<String> types = resultsPlace.getTypes();
//          todo: create else branch to put restaurant data in event even if it exists in restaurant repository
            if(!(types.contains("gas_station") || types.contains("convenience_store"))){
                if(!restaurantRepository.existsById(placeID)){
                    restaurantRepository
                            .save(new Restaurant(placeID, newEvent));
                }
                Optional<Restaurant> possibleRestaurant = restaurantRepository.findById(placeID);
                restaurants.add(possibleRestaurant.get());
            }

        }

        newEvent.setAvailableRestaurants(restaurants);
        eventRepository.save(newEvent);

        return ResponseEntity.status(200).build();
    }

    @PostMapping("join")
    public ResponseEntity<?> joinEvent(
            @RequestBody JoinEventDTO joinEventDTO,
            HttpServletRequest request
            ) throws URISyntaxException, IOException, InterruptedException {

        User user = userService.getUserFromRequest(request);
        Event event = eventRepository
                .findByEntryCode(joinEventDTO.getEntryCode())
                .orElseThrow(() -> new RoomCodeDoesNotExistException("Room Code Does Not Exist. Please try again"));

        if(event.getUsers().contains(user)){
            throw new UserAlreadyJoinedEventException("User has already joined this event");
        }

        List<User> moreUsers = event.getUsers();
        moreUsers.add(user);
        event.setUsers(moreUsers);
        eventRepository.save(event);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeRestaurant(
            @RequestBody UserLikesDTO userLikesDTO,
            HttpServletRequest request
            ) throws URISyntaxException, IOException, InterruptedException {

        Event event = eventRepository
                .findById(userLikesDTO.getEventId())
                .orElseThrow(() -> new EventDoesNotExistException("Event does not exist"));

        User user = userService.getUserFromRequest(request);
        userLikesDTO.setUserId(user.getId());

        // Process and save user likes within the event from the method in eventService
        eventService.saveLikedRestaurant(userLikesDTO);

        //Check if any restaurant has been liked by all users
        String mutuallyLikedRestaurant = eventService.getMutuallyLikedRestaurant(userLikesDTO);
        if (mutuallyLikedRestaurant != null) {
            System.out.println("Mutually Liked Restaurant: " + mutuallyLikedRestaurant);
            event.setMutuallyLikedRestaurant(mutuallyLikedRestaurant);
            eventRepository.save(event);
        } else {
            System.out.println("No Mutually Liked Restaurants");
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
