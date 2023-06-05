package org.launchcode.TasteBuddiesServer.models.place;

import org.launchcode.TasteBuddiesServer.models.place.photo.Photo;

import java.util.List;

public class ResultsPlace {
    private String place_id;
    private String name;
    private String formatted_address;
    private List<String> types;
    private Photo[] photos;

    public ResultsPlace(String place_id, String name, String formatted_address, List<String> types, Photo[] photos) {
        this.place_id = place_id;
        this.name = name;
        this.formatted_address = formatted_address;
        this.types = types;
        this.photos = photos;
    }

    public String getPlace_id() {
        return place_id;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public Photo[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photo[] photos) {
        this.photos = photos;
    }
}
