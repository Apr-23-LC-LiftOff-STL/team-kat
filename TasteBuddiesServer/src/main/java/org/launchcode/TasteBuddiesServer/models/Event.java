package org.launchcode.TasteBuddiesServer.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Event {

    // six uppercase letters?
    @NotNull
    private String eventCode;

    // TODO add mapping
    @ManyToMany(mappedBy = "events")
    private final List<User> users = new ArrayList<>();

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private byte radiusMiles;

    @NotNull
    private LocalDateTime dateTime;

    @NotNull
    private boolean isActive;

//    @NotNull
//    private List<Restaurant> choises = new ArrayList<>();


    // TODO figure out how to properly represent this relationship
    // Part of me thinks it's best to just make a UserLikes class
    // to simplify the db relationship to a couple one-to-many relationships
    private final Map<User, List<Restaurant>> likes = new HashMap<>();


    // Constructors

    public Event() { }

    public Event(String eventCode, double latitude, double longitude, byte radiusMiles, LocalDateTime dateTime) {
        this.eventCode = eventCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radiusMiles = radiusMiles;
        this.dateTime = dateTime;
        this.isActive = true;
    }

    // Getters and Setters

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public List<User> getUsers() {
        return users;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public byte getRadiusMiles() {
        return radiusMiles;
    }

    public void setRadiusMiles(byte radiusMiles) {
        this.radiusMiles = radiusMiles;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        // TODO update logic either here or on the frontend to close this event when it hits it's date and time
        isActive = active;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    // Equals and HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventCode, event.eventCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventCode);
    }
}
