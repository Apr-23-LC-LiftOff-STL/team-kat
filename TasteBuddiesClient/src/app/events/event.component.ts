import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Event } from 'src/models/event';
import { EventService } from 'src/services/event.service';
import { PlacesService } from 'src/services/places.service';
import { UserLikesDTO } from 'src/models/DTO/user-likes-dto';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  event$: Observable<any>;
  restaurants: Array<{id: string}>;
  currentRestaurant: string;
  restaurantDetails: {
    place_id: string,
    name: string,
    formatted_address: string,
    types: Array<string>,
    photos: Array<{
      photo_reference: string;
      html_attributions: Array<string>;
      height: number;
      width: number;
  }>
  };
  currentPhoto: any;
  event: Event;

  isPhotoLoading: boolean = true;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventService,
    private placesService: PlacesService,
    private userService: UserService,
    
    ) { }

  ngOnInit(): void {

    this.event$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) => {
        return this.eventService.getEvent(Number.parseInt(params.get('id')!))
      })
    );

    this.event$.subscribe({
      next: res => {
        this.event = res;
        this.restaurants = this.event.restaurants;
        this.nextRestaurant();
      },
      error: e => {
        console.error(e);
      }
    })
  }

  yesToRestaurant(choice: boolean): void {
    this.saveLikedRestaurant(this.currentRestaurant, choice);
    this.nextRestaurant();
  }

  private nextRestaurant(): void {
    this.currentRestaurant = this.restaurants.shift()?.id!;
    this.placesService.getRestaurantDetails(this.currentRestaurant).subscribe({
      next: res => {
        this.restaurantDetails = res;
        this.restaurantDetails.types = this.filterTypes(this.restaurantDetails.types);
        this.restaurantDetails.formatted_address = this.formatAddress(this.restaurantDetails.formatted_address);
        this.loadPhoto(this.restaurantDetails.photos[0].photo_reference);
      },
      error: e => {
        console.error(e);
      }
    });
  }

  //Send liked restaurant info to server
  saveLikedRestaurant(restaurantId: string, isLike: boolean): void {

    const userLikesDTO = new UserLikesDTO(
      String(this.event.id),
      restaurantId,
      isLike
    );

    this.eventService.saveLike(userLikesDTO).subscribe({
      next: res => {}, //doesn't perform any specific actions when save is successful
      error: e => {
        console.error(e); //Displays error when save is unsuccessful
      }
    });
  }

  private filterTypes(types: Array<string>): Array<string> {
    const IGNORED_TYPES = ['point_of_interest', 'establishment', 'food', 'storage'];
    types = types.filter(type => !IGNORED_TYPES.includes(type));

    let formattedTypes = [];

    for (let t of types) {
      t = t.replace('meal_takeaway', 'takeout');
      t = t.replace('_', ' ');
      formattedTypes.push(t);
    }
    return formattedTypes;
  }

  private formatAddress(address: string): string {
    address = address.replace(', ', ',\n').replace(', USA', '');
    return address;
  }
  
  private loadPhoto(photo_reference: string): void {
    this.isPhotoLoading = true;

    this.placesService.getPhoto(photo_reference, 400).subscribe({
      next: res => {
        this.createImageFromBlob(res);
      },
      error: e => {
        console.error(e);
      }
    })
  }

  private createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.currentPhoto = reader.result;
    }, false);
    
    if (image) {
      reader.readAsDataURL(image);
      this.isPhotoLoading = false;
    }
  }
}
