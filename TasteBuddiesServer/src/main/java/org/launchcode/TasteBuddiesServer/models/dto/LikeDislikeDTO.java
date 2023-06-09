package org.launchcode.TasteBuddiesServer.models.dto;

import org.launchcode.TasteBuddiesServer.models.Restaurant;
import org.launchcode.TasteBuddiesServer.models.UserLikes;

import java.util.List;
import java.util.stream.Collectors;

public class LikeDislikeDTO {

    List<String> likes;
    List<String> dislikes;

    public LikeDislikeDTO(List<String> likes, List<String> dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public LikeDislikeDTO(UserLikes userLikes) {
        this.likes = userLikes.getLikedRestaurants().stream()
                .map(Restaurant::getId)
                .collect(Collectors.toList());
        this.dislikes = userLikes.getDislikedRestaurants().stream()
                .map(Restaurant::getId)
                .collect(Collectors.toList());
    }

    public LikeDislikeDTO() {
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public List<String> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<String> dislikes) {
        this.dislikes = dislikes;
    }
}
