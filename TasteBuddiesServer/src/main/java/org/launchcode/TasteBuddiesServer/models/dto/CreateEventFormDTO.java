package org.launchcode.TasteBuddiesServer.models.dto;

import java.util.Date;

public class CreateEventFormDTO {

    private String location;
    private String searchRadius;
    private Date mealTime;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }

    public Date getMealTime() {
        return mealTime;
    }

    public void setMealTime(Date mealTime) {
        this.mealTime = mealTime;
    }
}
