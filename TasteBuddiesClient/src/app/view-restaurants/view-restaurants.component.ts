import { Component, OnInit } from '@angular/core';
import { GooglePlacesService } from '../../services/google-places.service';
import { Loader } from '@googlemaps/js-api-loader';
import { User } from 'src/models/user';

@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit {
  restaurants: any[] = [];
  currentIndex = 0;
  likedRestaurantsUser1: any[] = [];
  likedRestaurantsUser2: any[] = [];
  mutuallyLikedRestaurants: any[] =[];
  endOfList = false;
  user1Completed = false;
  user2Completed = false;
  
  constructor(private googlePlacesService: GooglePlacesService) { }
  
  ngOnInit() {
    
    
    const loader = new Loader({
      apiKey: 'AIzaSyDTDdRCcrr3w5o6H_DdVHS7twjKKuAljeQ',
      version: 'weekly',
      libraries: ['places']
    });
    
    loader.load().then(() => {
      // Google Maps JavaScript API loaded and initialized
      // Call the Google Places service to fetch nearby places
      this.fetchNearbyPlaces();
    }).catch((error) => {
      console.log('Failed to load Google Maps JavaScript API:', error);
    })
  }

  fetchNearbyPlaces() {
    //Parameters of getNearbyPlaces latLng: string, radius: string, keyWord: string
    const latLng = "38.908, -90.043"; //Bethalto, IL Example
    const radius = "1000";
    const keyword = ""; //leave blank to call all restaurants in the area

    // Call the Google Places service to fetch nearby places
    this.googlePlacesService.getNearbyPlaces(latLng, radius, keyword)
      .then(places => {
        // Handle the returned places data as needed
        this.restaurants = places.results;
        console.log(this.restaurants);
        

        // for (const restaurant of this.restaurants) {
          
        // }
        const photo = places.photos[0].getUrl({maxWidth: 250, maxHeight: 35})
        
        // this.getRestaurantPhotos();
      })
      .catch(error => {
        // Handle the error if needed
        console.log('Error occurred while fetching nearby places:', error);
      });
  }

  getRestaurantPhotos() {
    const placeService = new google.maps.places.PlacesService(document.createElement('div'));

    for (const restaurant of this.restaurants) {
      if (restaurant.photos && restaurant.photos.length > 0) {
        const photo = restaurant.photos[0];
        const options = {
          maxHeight: 500,
          maxWidth: 500
        }
        const photoUrl = photo.getUrl(options);
        restaurant.photoUrl = photoUrl;
      }
    }
  }

  //Start User voting Logic//

  //Logic for hitting the like button
  likeRestaurant(user: string) {
    if (user === 'user1') {
      this.likedRestaurantsUser1.push(this.restaurants[this.currentIndex]);
      this.nextRestaurant();
      if(this.currentIndex >= this.restaurants.length) {
        this.endOfList = true;
        this.user1Completed = true;
        this.endTurn('user1');
      }
    } else if (user === 'user2') {
      this.likedRestaurantsUser2.push(this.restaurants[this.currentIndex]);
      this.nextRestaurant();
      if(this.currentIndex >= this.restaurants.length) {
        this.endOfList = true;
        this.endTurn('user2');
      }
    }
  }
//logic for hitting the dislike button
  dislikeRestaurant() {
    this.nextRestaurant();
  }
//Itterate through restaurants
  nextRestaurant() {
    this.currentIndex++;
    if(this.currentIndex >= this.restaurants.length) {
      //Reached the end of the list, handle appropriately
      // this.endOfList = true;
    }
  }

  //End turn for users
  endTurn(user: string) {
    if (user === 'user1'){
      this.user1Completed = true;
    } else if (user === 'user2') {
      this.user2Completed = true;
      this.findMutuallyLikedRestaurants();
    }
  }

//Code for finding mutual likes in the likedRestaurantsUser1 and likedRestaurantsUser2 arrays and pushing them to the mutuallyLikedRestaurants Array.
  findMutuallyLikedRestaurants() {
    for (const restaurant1 of this.likedRestaurantsUser1) {
      const matchingRestaurant = this.likedRestaurantsUser2.find(function(restaurant2){
        return restaurant2.place_id === restaurant1.place_id;
      });
      if(matchingRestaurant) {
        this.mutuallyLikedRestaurants.push(restaurant1);
      }
    }
  }



//Use in between users
  restartViewingForUser2() {
    this.currentIndex = 0;
    this.endOfList = false;
    // this.findMutuallyLikedRestaurants();
  }
//Start a new event
  restartEvent() {
    this.currentIndex = 0;
    this.endOfList = false;
    this.likedRestaurantsUser1 = [];
    this.likedRestaurantsUser2 = [];
    this.mutuallyLikedRestaurants = [];
  }

}
