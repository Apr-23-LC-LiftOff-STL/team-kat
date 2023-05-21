package org.launchcode.TasteBuddiesServer.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User extends AbstractEntity implements UserDetails {


    @NotBlank(message = "Display name required.")
    @Column(nullable = false)
    private String displayName;

    @Email(message = "A valid email required.")
    @NotBlank(message = "Email required")
    @Column(unique = true, nullable = false)
    private String email;


    @NotBlank(message = "Password required.")
    @Size(min = 6,max = 30, message = "Password must be between 6 and 30 characters.")
    @Column(nullable = false)
    private String password;


    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserLikes userLikes;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AuthorityEntity> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = new HashSet<>();
        this.authorities.add(new AuthorityEntity("BASIC_USER"));
    }

    public User(String displayName, String email, String password) {
        this();
        this.displayName = displayName;
        this.email = email;
        this.password = "{bcrypt}" + encoder.encode(password);
    }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }



    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public UserLikes getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(UserLikes userLikes) {
        this.userLikes = userLikes;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Set<AuthorityEntity> getAuthorities() { return authorities; }
    public void setAuthorities(Set<AuthorityEntity> authorities) {
        this.authorities = authorities;
    }

    // A note on password handling: Spring's DelegatingPasswordEncoder
    // required a string stored as part of the password designating
    // which encoder was used on the password in order to check incoming
    // password with the same encoder. Hence, the {bcrypt} being appended.
    @Override
    public String getPassword() { return this.password; }
    public void setPassword(String password) {
        this.password = "{bcrypt}" + encoder.encode(password);
    }

    @Override
    public boolean isAccountNonExpired() { return accountNonExpired; }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() { return accountNonLocked; }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
