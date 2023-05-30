package org.launchcode.TasteBuddiesServer.models.geocode;

public class ResultsGC {
    private Geometry geometry;

    public ResultsGC(Geometry geometry) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
