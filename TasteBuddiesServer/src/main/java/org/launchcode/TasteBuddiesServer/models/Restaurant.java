package org.launchcode.TasteBuddiesServer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;


@Entity
public class Restaurant {

    @Id
    @NotNull
    private String apiID;
    private String name;
    private int priceLevel;
    private float rating;

    public Restaurant() {
    }

    public Restaurant(String apiID) {
        this.apiID = apiID;
    }

    public String getApiID() {
        return apiID;
    }

    public void setApiID(String apiID) {
        this.apiID = apiID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(apiID, that.apiID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiID);
    }
}
