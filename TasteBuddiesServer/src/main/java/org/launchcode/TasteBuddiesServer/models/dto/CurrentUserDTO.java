package org.launchcode.TasteBuddiesServer.models.dto;

import org.launchcode.TasteBuddiesServer.models.AbstractEntity;
import org.launchcode.TasteBuddiesServer.models.User;
import org.launchcode.TasteBuddiesServer.models.UserLikes;

import java.util.List;
import java.util.stream.Collectors;

public class CurrentUserDTO extends OtherUserDTO {
    private String email;
    private List<Integer> eventIDs;
    private List<UserLikes> userLikes;

    public CurrentUserDTO(int id, String displayName, String email, List<UserLikes> userLikes) {
        super(id, displayName);
        this.email = email;
        this.userLikes = userLikes;
    }

    public CurrentUserDTO(User user) {
        this(user.getId(), user.getDisplayName(), user.getEmail(), user.getUserLikes());
        this.eventIDs = user.getEvents()
                .stream()
                .map(AbstractEntity::getId)
                .collect(Collectors.toList());
    }

    public int getId() {
        return super.getId();
    }

    public void setId(int id) {
        super.setId(id);
    }

    public String getDisplayName() {
        return super.getDisplayName();
    }

    public void setDisplayName(String displayName) {
        super.setDisplayName(displayName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getEventIDs() {
        return eventIDs;
    }

    public void setEventIDs(List<Integer> eventIDs) {
        this.eventIDs = eventIDs;
    }
}
