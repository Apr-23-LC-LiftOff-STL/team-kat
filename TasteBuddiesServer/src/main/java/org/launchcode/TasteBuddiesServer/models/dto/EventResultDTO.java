//package org.launchcode.TasteBuddiesServer.models.dto;
//
//import org.launchcode.TasteBuddiesServer.models.Event;
//import org.launchcode.TasteBuddiesServer.models.User;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class EventResultDTO {
//    private int eventId;
//    private String mutuallyLikedRestaurant;
//    private List<OtherUserDTO> otherUsers;
//    private CurrentUserDTO currentUser;
//    private int totalRestaurants;
//    private int restaurantsVoted;
//
//    public EventResultDTO(Event event, User currentUser) {
//        this.eventId = event.getId();
//        this.mutuallyLikedRestaurant = event.getMutuallyLikedRestaurant();
//        this.currentUser = new CurrentUserDTO(currentUser);
//        this.otherUsers = event.getUsers()
//                .stream()
//                .filter(user -> user.getId() != currentUser.getId())
//                .map(OtherUserDTO::new)
//                .collect(Collectors.toList());
//    }
//
//    public int getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(int eventId) {
//        this.eventId = eventId;
//    }
//
//    public String getMutuallyLikedRestaurant() {
//        return mutuallyLikedRestaurant;
//    }
//
//    public void setMutuallyLikedRestaurant(String mutuallyLikedRestaurant) {
//        this.mutuallyLikedRestaurant = mutuallyLikedRestaurant;
//    }
//
//    public List<OtherUserDTO> getOtherUsers() {
//        return otherUsers;
//    }
//
//    public void setOtherUsers(List<OtherUserDTO> otherUsers) {
//        this.otherUsers = otherUsers;
//    }
//
//    public CurrentUserDTO getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(CurrentUserDTO currentUser) {
//        this.currentUser = currentUser;
//    }
//
//    public int getTotalRestaurants() {
//        return totalRestaurants;
//    }
//
//    public void setTotalRestaurants(int totalRestaurants) {
//        this.totalRestaurants = totalRestaurants;
//    }
//
//    public int getRestaurantsVoted() {
//        return restaurantsVoted;
//    }
//
//    public void setRestaurantsVoted(int restaurantsVoted) {
//        this.restaurantsVoted = restaurantsVoted;
//    }
//}
