package org.launchcode.TasteBuddiesServer.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthorityEntity implements GrantedAuthority {

    @Id
    private String authority;

    public AuthorityEntity(String role) {
        this.authority = role;
    }

    @Override
    public String getAuthority() {
        return null;
    }

}
