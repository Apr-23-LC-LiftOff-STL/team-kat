package org.launchcode.TasteBuddiesServer.models.dto;

import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.UserLikes;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is to return users, other than the logged-in user.
 * It prevents sending the events the other user is in,
 * and the other user's email
 */

public class OtherUserDTO {

    private int id;
    private String displayName;
    private List<String> likes;
    private List<String> dislikes;

    public OtherUserDTO(int id, String displayName, List<String> likes, List<String> dislikes) {
        this.id = id;
        this.displayName = displayName;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public OtherUserDTO(int id, String displayName, UserLikes userLikes) {
        this(
                id,
                displayName,
                userLikes.getLikedRestaurants()
                        .stream()
                        .map(Restaurant::getId)
                        .collect(Collectors.toList()),
                userLikes.getDislikedRestaurants()
                        .stream()
                        .map(Restaurant::getId)
                        .collect(Collectors.toList())
                );
    }

    public OtherUserDTO(User user, int eventId) {
        this(
            user.getId(),
            user.getDisplayName(),
            user.getUserLikes().stream()
                .filter(ul -> ul.getEvent().getId() == eventId)
                .findFirst()
                .orElse(new UserLikes())
        );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }
}
