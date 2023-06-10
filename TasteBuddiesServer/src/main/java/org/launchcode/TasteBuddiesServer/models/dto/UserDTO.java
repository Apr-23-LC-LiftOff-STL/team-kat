package org.launchcode.TasteBuddiesServer.models.dto;

import org.launchcode.TasteBuddiesServer.models.AbstractEntity;
import org.launchcode.TasteBuddiesServer.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private int id;
    private String email;
    private String displayName;
    private List<Integer> eventIDs;

    public UserDTO(int id, String email, String displayName, List<Integer> eventIDs) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.eventIDs = eventIDs;
    }

    public UserDTO(User user) {
        this(
                user.getId(),
                user.getEmail(),
                user.getDisplayName(),
                user.getEvents().stream()
                        .map(AbstractEntity::getId)
                        .collect(Collectors.toList())
        );
    }

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Integer> getEventIDs() {
        return eventIDs;
    }

    public void setEventIDs(List<Integer> eventIDs) {
        this.eventIDs = eventIDs;
    }
}
