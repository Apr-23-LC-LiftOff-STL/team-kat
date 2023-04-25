package org.launchcode.TasteBuddiesServer.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    // TODO add validation for displayName
    @NotNull
    @NotBlank
    private String displayName;

    @Email
    @NotNull
    private String email;

    @NotNull
    @Min(message = "Password must be at least 4 characters", value=4)
    @Max(message = "Password must be 20 characters or fewer", value=20)
    private String passwordHash;

    @ManyToMany
    private List<Event> events = new ArrayList<>();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {

    }

    public User(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.passwordHash = encoder.encode(password);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String password) {
        this.passwordHash = encoder.encode(password);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, this.passwordHash);
    }

}
