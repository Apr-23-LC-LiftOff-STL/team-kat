package org.launchcode.TasteBuddiesServer.models.dto;

public class UserDislikeDTO {
    private final String userId;
    private final String eventId;
    private final String restaurantId;

    public UserDislikeDTO(String userId, String eventId, String restaurantId) {
        this.userId = userId;
        this.eventId = eventId;
        this.restaurantId = restaurantId;
    }

    public String getUserId() {
        return userId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }
}
