package org.launchcode.TasteBuddiesServer.controller;

import com.google.gson.Gson;
import org.launchcode.TasteBuddiesServer.data.EventRepository;
import org.launchcode.TasteBuddiesServer.data.RestaurantRepository;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.dto.EventDTO;
import org.launchcode.TasteBuddiesServer.models.geocode.TranscriptGC;
import org.launchcode.TasteBuddiesServer.models.nearbySearch.TranscriptNB;
import org.launchcode.TasteBuddiesServer.models.place.TranscriptPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;

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
    @Value("${apiKey}")
    private String APIKey;
//    @Autowired
//    private Gson gson;

    @PostMapping("")
    public ResponseEntity<?> collectRestaurantData(@RequestBody EventDTO data){
        TranscriptGC transcriptGC = new TranscriptGC();
        TranscriptNB transcriptNB = new TranscriptNB();
        TranscriptPlace transcriptPlace = new TranscriptPlace();
        String URLGC = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        String URLNB = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=restaurant&location=";
        String URLPlace = "https://maps.googleapis.com/maps/api/place/details/json?place_id=";
        Gson gson = new Gson();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI()).build();
        return ResponseEntity.status(200).build();
    }
}
