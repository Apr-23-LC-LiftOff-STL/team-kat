package org.launchcode.TasteBuddiesServer.models.dto;

import org.launchcode.TasteBuddiesServer.models.AbstractEntity;
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
    private List<UserLikes> userLikes;

    public OtherUserDTO(int id, String displayName, List<UserLikes> userLikes) {
        this.id = id;
        this.displayName = displayName;
        this.userLikes = userLikes;
    }

    public OtherUserDTO(User user) {
        this.id = user.getId();
        this.displayName = user.getDisplayName();
        this.userLikes = user.getUserLikes();
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

    public List<UserLikes> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<UserLikes> userLikes) {
        this.userLikes = userLikes;
    }
}
