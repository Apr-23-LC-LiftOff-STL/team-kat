package org.launchcode.TasteBuddiesServer.services;

import com.google.gson.Gson;
import org.launchcode.TasteBuddiesServer.models.geocode.Location;
import org.launchcode.TasteBuddiesServer.models.geocode.TranscriptGC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeocodeService {

    @Value("${apiKey}")
    private String APIKey;

    public Location getGeocodeFromAddress(String address) throws URISyntaxException, IOException, InterruptedException {

        final String URLGC = "https://maps.googleapis.com/maps/api/geocode/json?address=";

        Gson gson = new Gson();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest
                .newBuilder()
                .uri(new URI(URLGC+address+"&key="+APIKey))
                .build();
        HttpResponse<String> getResponse = httpClient
                .send(getRequest, HttpResponse.BodyHandlers.ofString());

        return gson
                .fromJson(getResponse.body(), TranscriptGC.class)
                .getResults()
                .get(0)
                .getGeometry()
                .getLocation();
    }
}
