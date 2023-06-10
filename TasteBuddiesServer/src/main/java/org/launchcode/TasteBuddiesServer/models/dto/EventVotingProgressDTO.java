package org.launchcode.TasteBuddiesServer.models.dto;

import java.util.HashMap;
import java.util.Map;

public class EventVotingProgressDTO {
    Map<String, Integer> userVotes = new HashMap<>();
    Integer numberOfAvailableRestaurant;
    public EventVotingProgressDTO(){}

    public Map<String, Integer> getUserVotes(Map<String, Integer> userVotes) {
        return this.userVotes;
    }

    public void setUserVotes(Map<String, Integer> userVotes) {
        this.userVotes = userVotes;
    }

    public Integer getNumberOfAvailableRestaurant(int size) {
        return numberOfAvailableRestaurant;
    }

    public void setNumberOfAvailableRestaurant(Integer numberOfAvailableRestaurant) {
        this.numberOfAvailableRestaurant = numberOfAvailableRestaurant;
    }
}

