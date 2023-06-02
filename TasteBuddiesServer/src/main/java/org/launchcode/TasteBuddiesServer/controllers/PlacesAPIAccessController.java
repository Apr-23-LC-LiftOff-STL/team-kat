package org.launchcode.TasteBuddiesServer.controllers;

import org.launchcode.TasteBuddiesServer.models.dto.PlacesRequestDTO;
import org.launchcode.TasteBuddiesServer.services.PlaceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/restaurant")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true")
public class PlacesAPIAccessController {
    private final PlaceService placeService;

    @Value("${apiKey}")
    private String APIKey;

    public PlacesAPIAccessController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public ResponseEntity<?> makeRequestToPlacesAPI(
            @RequestParam String placeID
    ) {
        try {
            return ResponseEntity
                    .status(200)
                    .body(placeService.getRestaurantFromPlaceID(placeID));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> makeRequestToPlacesAPI(
            HttpServletRequest request,
            @RequestBody PlacesRequestDTO placesRequestDTO
    ) {
        try {
            return ResponseEntity
                    .status(200)
                    .body(placeService.getRestaurantFromPlaceID(placesRequestDTO.getPlaceID()));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity
                    .status(400)
                    .build();
        }
    }
}