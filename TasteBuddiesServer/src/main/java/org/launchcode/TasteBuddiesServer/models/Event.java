package org.launchcode.TasteBuddiesServer.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String entryCode;

    @NotNull
    private String location;

    @NotNull
    private String searchRadius;

    //fetchtype to "LAZY" for performance
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//defining a many-to-one relationship between two entities and specifying that the related entity should be fetched lazily to optimize performance.
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_events",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();


    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Event(){

    }

    public Event(String entryCode,String location, String searchRadius, User user) {
        this.entryCode = entryCode;
        this.location = location;
        this.searchRadius = searchRadius;
        this.user = user;
        this.createdDate = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSearchRadius() {
        return searchRadius;
    }

    public void setSearchRadius(String searchRadius) {
        this.searchRadius = searchRadius;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}