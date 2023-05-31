package org.launchcode.TasteBuddiesServer.services;

import com.google.gson.Gson;
import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.place.ResultsPlace;
import org.launchcode.TasteBuddiesServer.models.place.TranscriptPlace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlaceService {

    @Value("${apiKey}")
    private String APIKey;

    public ResultsPlace getRestaurantFromPlaceID(String placeID) throws URISyntaxException, IOException, InterruptedException {

        final String URLPlace = "https://maps.googleapis.com/maps/api/place/details/json";
        TranscriptPlace transcriptPlace;
        Gson gson = new Gson();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequestPlace = HttpRequest.newBuilder()
                .uri(new URI(
                        URLPlace +
                                "?place_id=" + placeID +
                                "&key=" + APIKey))
                .build();
        HttpResponse<String> getResponsePlace = httpClient.send(getRequestPlace, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(getResponsePlace.body(), TranscriptPlace.class).getResult();

    }

    public List<ResultsPlace> getRestaurantsFromPlaceIDs(List<String> placeIDs) {

        List<ResultsPlace> places = new ArrayList<>();

        for (String placeId : placeIDs) {
            try {
                places.add(this.getRestaurantFromPlaceID(placeId));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return places;
    }

}
