package org.launchcode.TasteBuddiesServer.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class User extends AbstractEntity {

    // TODO add validation for displayName
    @NotNull
    @NotBlank
    private String displayName;

    // TODO add validation for email
    @Email
    @NotNull
    private String email;

    @NotNull
    @Min(message = "Password must be at least 4 characters", value=4)
    @Max(message = "Password must be 20 characters or fewer", value=20)
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
