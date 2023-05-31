package org.launchcode.TasteBuddiesServer.services;

import com.google.gson.Gson;
import org.launchcode.TasteBuddiesServer.models.geocode.Location;
import org.launchcode.TasteBuddiesServer.models.nearbySearch.TranscriptNB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NearbyService {

    @Value("${apiKey}")
    private String APIKey;

    String URLNB = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?type=restaurant";

    public List<String> getNearbyIDsFromLocationAndSearchRadius (
            Location location,
            String searchRadius
    ) throws URISyntaxException, IOException, InterruptedException
    {

        TranscriptNB transcriptNB;

        Gson gson = new Gson();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequestNB = HttpRequest.newBuilder()
                .uri(new URI(
                        URLNB +
                                "&location=" + location.getLat() + "%2C" + location.getLng() +
                                "&radius=" + searchRadius +
                                "&key="+APIKey))
                .build();

        HttpResponse<String> getResponseNB = httpClient.send(getRequestNB, HttpResponse.BodyHandlers.ofString());
        transcriptNB = gson.fromJson(getResponseNB.body(), TranscriptNB.class);

        List<String> placeIDs = new ArrayList<>();
        String pageToken = transcriptNB.getNext_page_token();

        for(int i=0; i < transcriptNB.getResults().size(); i++){
            String place_id = transcriptNB.getResults().get(i).getPlace_id();
            placeIDs.add(place_id);
        }

        // TODO refactor this to schedule execution of the next set of results so it isn't so slow
        // https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java`
        while(pageToken != null){
            Date currentDate = new Date();
            Date futureDate = new Date();
            futureDate.setTime(currentDate.getTime()+2000);
            while(futureDate.getTime() > currentDate.getTime()){
                currentDate = new Date();
            }
            HttpRequest getRequestNB2 = HttpRequest.newBuilder()
                    .uri(new URI(
                            URLNB +
                                    "&location=" + location.getLat() +
                                    "%2C" + location.getLng() +
                                    "&radius=" + searchRadius +
                                    "&key=" + APIKey +
                                    "&pagetoken=" + pageToken))
                    .build();
            HttpResponse<String> getResponseNB2 = httpClient.send(getRequestNB2, HttpResponse.BodyHandlers.ofString());
            transcriptNB = gson.fromJson(getResponseNB2.body(), TranscriptNB.class);

            pageToken = transcriptNB.getNext_page_token();

            for(int j=0; j < transcriptNB.getResults().size(); j++){
                String place_id = transcriptNB
                        .getResults()
                        .get(j)
                        .getPlace_id();
                placeIDs.add(place_id);
            }
        }

        return placeIDs;
    }
}
