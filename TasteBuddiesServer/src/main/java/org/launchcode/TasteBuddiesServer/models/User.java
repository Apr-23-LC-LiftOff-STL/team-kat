package org.launchcode.TasteBuddiesServer.models;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends AbstractEntity implements UserDetails {

    // TODO add validation for displayName
    @NotNull
    private String displayName;

    // TODO add validation for email
    @NotNull
    private String email;

    // TODO add validation for password
    @NotNull
    private String password;

    @OneToMany()
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
