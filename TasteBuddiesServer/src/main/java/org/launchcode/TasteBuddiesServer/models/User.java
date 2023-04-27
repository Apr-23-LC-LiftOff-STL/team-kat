package org.launchcode.TasteBuddiesServer.models;

import org.hibernate.collection.internal.PersistentSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
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

    @NotNull
    private String password;

    @OneToMany()
    private final Set<AuthorityEntity> authorites = new HashSet<>();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() { }

    public User(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = encoder.encode(password);
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Set<AuthorityEntity> getAuthorities() {
        return authorites;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, this.getPassword());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
