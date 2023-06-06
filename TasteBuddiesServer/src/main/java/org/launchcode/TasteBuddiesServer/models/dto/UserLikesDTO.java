package org.launchcode.TasteBuddiesServer.models.dto;

public class UserLikesDTO {
    private  Integer userId;
    private  Integer eventId;
    private  String restaurantId;

    private boolean isLike;

    public UserLikesDTO(Integer userId, Integer eventId, String restaurantId, boolean isLike) {
        this.userId = userId;
        this.eventId = eventId;
        this.restaurantId = restaurantId;
        this.isLike = isLike;
    }

    public UserLikesDTO() {}

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
    public boolean isLike() {
        return isLike;
    }
    public void setLike(boolean like) {
        isLike = like;
    }
}
