package org.launchcode.TasteBuddiesServer.models.place.photo;

import java.util.List;
import java.util.Objects;

public class Photo {
    private String photo_reference;
    private List<String> html_attributions;
    private int height;
    private int width;

    public Photo(String photo_reference, List<String> html_attributions, int height, int width) {
        this.photo_reference = photo_reference;
        this.html_attributions = html_attributions;
        this.height = height;
        this.width = width;
    }

    public Photo() {
    }

    public String getPhoto_reference() {
        return photo_reference;
    }

    public void setPhoto_reference(String photo_reference) {
        this.photo_reference = photo_reference;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(photo_reference, photo.photo_reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photo_reference);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "photo_reference='" + photo_reference + '\'' +
                '}';
    }
}
