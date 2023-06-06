package org.launchcode.TasteBuddiesServer.models.dto;

public class UserLikesDTO {
    private  Integer userId;
    private  Integer eventId;
    private  String restaurantId;

    public UserLikesDTO(Integer userId, Integer eventId, String restaurantId) {
        this.userId = userId;
        this.eventId = eventId;
        this.restaurantId = restaurantId;
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

    @Override
    public String toString() {
        return "UserLikesDTO{" +
                "userId=" + userId +
                ", eventId=" + eventId +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}
