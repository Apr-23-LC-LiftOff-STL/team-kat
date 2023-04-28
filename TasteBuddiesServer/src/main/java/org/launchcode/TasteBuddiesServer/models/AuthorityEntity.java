package org.launchcode.TasteBuddiesServer.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
public class AuthorityEntity implements GrantedAuthority {

    @Id
    @GeneratedValue
    private int id;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public AuthorityEntity(int id, String authority, List<User> users) {
        this.id = id;
        this.authority = authority;
        this.users = users;
    }

    public AuthorityEntity() {
    }

    public AuthorityEntity(String role) {
        this.authority = role;
    }

    @Override
    public String getAuthority() {
        return null;
    }

}
