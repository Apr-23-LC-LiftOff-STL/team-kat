package org.launchcode.TasteBuddiesServer.models;

import java.util.Objects;

public class Restaurant {
    private String id;
    private String name;
    private String address;

    public Restaurant(String id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Restaurant(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant)) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
