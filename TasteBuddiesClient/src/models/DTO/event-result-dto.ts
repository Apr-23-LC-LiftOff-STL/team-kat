import { User } from "../user";

export class EventResultDTO {
    eventId: number;
    mutuallyLikedRestaurant: string;
    otherUsers: User[];
    currentUser: User;
    totalRestaurants: number;
    restaurantsVoted: number;
  
    constructor(eventId: number, mutuallyLikedRestaurant: string, currentUser: User, otherUsers: User[], totalRestaurants: number, restaurantsVoted: number) {
      this.eventId = eventId;
      this.mutuallyLikedRestaurant = mutuallyLikedRestaurant;
      this.currentUser = currentUser;
      this.totalRestaurants = totalRestaurants;
      this.restaurantsVoted = restaurantsVoted;
    }
  }