package org.launchcode.TasteBuddiesServer.controller;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.launchcode.TasteBuddiesServer.data.UserRepository;
import org.launchcode.TasteBuddiesServer.models.dto.EventDTO;
import org.launchcode.TasteBuddiesServer.models.geocode.TranscriptGC;
import org.launchcode.TasteBuddiesServer.models.nearbySearch.TranscriptNB;
import org.launchcode.TasteBuddiesServer.models.place.TranscriptPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpRequest;
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
    @Value("${apiKey}")
    private String APIKey;

    @PostMapping("")
    public ResponseEntity<?> collectRestaurantData(@RequestBody EventDTO data)
            throws URISyntaxException, IOException, InterruptedException {
        TranscriptGC transcriptGC = new TranscriptGC();
        TranscriptNB transcriptNB = new TranscriptNB();
        TranscriptPlace transcriptPlace = new TranscriptPlace();

        String URLGC = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        String URLNB = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=restaurant&location=";
        String URLPlace = "https://maps.googleapis.com/maps/api/place/details/json?place_id=";

        Gson gson = new Gson();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(URLGC+data.getLocation()+"&key="+APIKey))
                .build();
        HttpResponse<String> getResponse = httpClient
                .send(getRequest, HttpResponse.BodyHandlers.ofString());

        transcriptGC = gson.fromJson(getResponse.body(), TranscriptGC.class);

        String lat = transcriptGC.getResults().get(0).getGeometry().getLocation().getLat();
        String lng = transcriptGC.getResults().get(0).getGeometry().getLocation().getLng();

        System.out.println(lat);

        return ResponseEntity.status(200).build();
    }
}
