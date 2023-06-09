package org.launchcode.TasteBuddiesServer.services;

import com.google.gson.Gson;
import org.launchcode.TasteBuddiesServer.models.place.ResultsPlace;
import org.launchcode.TasteBuddiesServer.models.place.TranscriptPlace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true")
@Service
public class PlaceService {

    @Value("${apiKey}")
    private String APIKey;

    public ResultsPlace getRestaurantFromPlaceID(String placeID) throws URISyntaxException, IOException, InterruptedException {

        final String URLPlace = "https://maps.googleapis.com/maps/api/place/details/json";
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

    public ResponseEntity<byte[]> getImageFromPhotoReference(String photoReference, int maxheight, int maxwidth)
            throws IOException, InterruptedException, URISyntaxException
    {
        StringBuilder stringBuilder = new StringBuilder();
        final String URLPlace = "https://maps.googleapis.com/maps/api/place/photo?";
        stringBuilder.append(URLPlace);
        stringBuilder.append("photo_reference=").append(photoReference);

        if (maxheight > 0) {
            stringBuilder.append("&maxheight=").append(maxheight);
        }
        if (maxwidth > 0) {
            stringBuilder.append("&maxwidth=").append(maxwidth);
        }

        stringBuilder.append("&key=").append(APIKey);
        String photoURL = stringBuilder.toString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.getForEntity(photoURL, byte[].class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .status(response.getStatusCode())
                .header("Content-Type", response.getHeaders().getContentType().toString())
                .body(response.getBody());

    }

}
