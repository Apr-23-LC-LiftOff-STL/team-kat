package org.launchcode.TasteBuddiesServer.controller;

import org.launchcode.TasteBuddiesServer.data.EventRepository;
import org.launchcode.TasteBuddiesServer.data.RestaurantRepository;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.dto.EventDTO;
import org.launchcode.TasteBuddiesServer.models.geocode.TranscriptGC;
import org.launchcode.TasteBuddiesServer.models.nearbySearch.TranscriptNB;
import org.launchcode.TasteBuddiesServer.models.place.TranscriptPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @PostMapping("")
    public ResponseEntity<?> collectRestaurantData(@RequestBody EventDTO data){
        TranscriptGC transcriptGC = new TranscriptGC();
        TranscriptNB transcriptNB = new TranscriptNB();
        TranscriptPlace transcriptPlace = new TranscriptPlace();

        return ResponseEntity.status(200).build();
    }
}
