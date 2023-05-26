import { Component, OnInit } from '@angular/core';
import { GooglePlacesService } from '../../services/google-places.service';
import { Loader } from '@googlemaps/js-api-loader';

@Component({
  selector: 'app-view-restaurants',
  templateUrl: './view-restaurants.component.html',
  styleUrls: ['./view-restaurants.component.css']
})
export class ViewRestaurantsComponent implements OnInit {
  restaurants: any[] = [];

  constructor(private googlePlacesService: GooglePlacesService) { }

  ngOnInit() {
    
    const loader = new Loader({
      apiKey: '',
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
    const radius = "2500";
    const keyword = ""; //leave blank to call all restaurants in the area

    // Call the Google Places service to fetch nearby places
    this.googlePlacesService.getNearbyPlaces(latLng, radius, keyword)
      .then(places => {
        // Handle the returned places data as needed
        this.restaurants = places.results;
        console.log(this.restaurants);
      })
      .catch(error => {
        // Handle the error if needed
        console.log('Error occurred while fetching nearby places:', error);
      });

  }

}
