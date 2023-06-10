import { User } from "../user";

export class EventResultDTO {
    eventId: number;
    mutuallyLikedRestaurant: string;
    users: Map<User, String[]>;
    totalAvailableRestaurants: number;
    restaurantsPerUser: { [userId: string]: number };
  
    constructor(eventId: number, mutuallyLikedRestaurant: string, users: Map<User, String[]>, totalAvailableRestaurants: number, restaurantsPerUser: { [userId: string]: number }) {
      this.eventId = eventId;
      this.mutuallyLikedRestaurant = mutuallyLikedRestaurant;
      this.users = users;
      this.totalAvailableRestaurants = totalAvailableRestaurants;
      this.restaurantsPerUser = restaurantsPerUser;
    }
  }