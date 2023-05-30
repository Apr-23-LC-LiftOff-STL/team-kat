package org.launchcode.TasteBuddiesServer.models.geocode;

public class Geometry {
    private Location location;

    public Geometry(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
