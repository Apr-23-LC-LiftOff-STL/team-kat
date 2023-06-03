package org.launchcode.TasteBuddiesServer.models.dto;

import java.util.Objects;

public class PlacesRequestDTO {
    private String placeID;

    public PlacesRequestDTO(String placeID) {
        this.placeID = placeID;
    }

    public PlacesRequestDTO() {
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacesRequestDTO that = (PlacesRequestDTO) o;
        return Objects.equals(placeID, that.placeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeID);
    }
}
