package org.launchcode.TasteBuddiesServer.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class Restaurant {

    @Id
    private String id;
    //The id field will store the place_id from the API call,

    @JsonBackReference
    @ManyToMany(mappedBy = "availableRestaurants", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();

    public Restaurant() {

    }

    public Restaurant(String id, Event event) {
        this.id = id;
        this.events.add(event);
    }

    public Restaurant(String id, List<Event> events) {
        this.id = id;
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, events);
    }
}
