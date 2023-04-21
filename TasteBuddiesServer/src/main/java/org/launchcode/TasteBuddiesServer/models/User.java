package org.launchcode.TasteBuddiesServer.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    // TODO add validation for displayName
    @NotNull
    private String displayName;

    // TODO add validation for email
    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

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

    public String getEmail() {
        return email;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, this.passwordHash);
    }

}
