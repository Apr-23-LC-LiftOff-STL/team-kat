package org.launchcode.TasteBuddiesServer.controllers;

import org.launchcode.TasteBuddiesServer.services.PlaceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/places")
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

    @GetMapping("restaurant")
    public ResponseEntity<?> getPlaceFromPlacesAPI(
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

    /**
     *
     * @param photo_reference - Places API photo reference
     * @param maxwidth int - optional, but at least one of the dimension parameters are required
     * @param maxheight - int - optional, but at least one of the dimension parameters are required
     * @return ResponseEntity<>
     *
     *
     */
    @GetMapping(
            value = "image"
    )
    public ResponseEntity<?> getImageFromPlacesAPI(
            @RequestParam String photo_reference,
            @RequestParam(required = false) Integer maxwidth,
            @RequestParam(required = false) Integer maxheight
    ) {
        if (maxheight == null && maxwidth == null) {
            return ResponseEntity.status(400).build();
        }

        if (maxwidth == null) {
            maxwidth = 0;
        }

        if (maxheight == null) {
            maxheight = 0;
        }

        try {
            ResponseEntity<byte[]> result = placeService.getImageFromPhotoReference(photo_reference, maxheight, maxwidth);
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }

        return ResponseEntity.status(500).build();

    }


}
