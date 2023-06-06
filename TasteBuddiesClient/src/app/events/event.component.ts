import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Event } from 'src/models/event';
import { EventService } from 'src/services/event.service';
import { PlacesService } from 'src/services/places.service';
import { UserLikesDTO } from 'src/models/DTO/user-likes-dto';
import { HttpClient } from '@angular/common/http';
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
    private http: HttpClient
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

    // TODO: Call event service to save result of choice, update position of user on backend. 
    this.saveLikedRestaurant(this.currentRestaurant);
    this.nextRestaurant();
  }

  private nextRestaurant(): void {
    this.currentRestaurant = this.restaurants.shift()?.id!;
    this.placesService.getRestaurantDetails(this.currentRestaurant).subscribe({
      next: res => {
        this.restaurantDetails = res;
        this.loadPhoto(this.restaurantDetails.photos[0].photo_reference)
      },
      error: e => {
        console.error(e);
      }
    });
  }

  //Send liked restaurant info to server
  saveLikedRestaurant(restaurantId: string): void {
    const EVENT_API = 'http://localhost:8080/api/event/';
    this.userService.getUser().subscribe(
      (user: any) => {
        const userId = user.id;
        const userLikesDTO = new UserLikesDTO(
          String(this.event.id),
          restaurantId,
          userId
          );
        }
        )
        console.log(this.event.id);
    
    this.http.post(EVENT_API + '${eventId}', UserLikesDTO).subscribe({
      next: res => {
        console.log('Restaurant liked successfully');
      },
      error: e => {
        console.error('Error liking restaurant:', Error);
      }
    })
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
