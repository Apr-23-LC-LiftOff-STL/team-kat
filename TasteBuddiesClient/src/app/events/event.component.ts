import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Event } from 'src/models/event';
import { EventService } from 'src/services/event.service';
import { PlacesService } from 'src/services/places.service';

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
    formatted_adress: string,
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

  private nextRestaurant(): void {
    this.currentRestaurant = this.restaurants.shift()?.id!;
    this.placesService.getRestaurantDetails(this.currentRestaurant).subscribe({
      next: res => {
        this.restaurantDetails = res;
        this.loadPhoto(this.restaurantDetails.photos[0].photo_reference)
        // this.loadPhoto('AZose0lbXPf2rHelg5TC_ackUKpNu3Vt5mZLgJ9WuX-B07W1QieYjlKKeiTlhNtgO1nMYlH_y4fNXB-kjvm2gl6KX_7l4gW3GemG5yNHgDJle7a0qHPlhsCabNdCl_5qwnhTm8PDfzmXx8WCH0F6bI-0MUKVLgFnrutTnXlzJyr9_zXlJLHH') // dummy request
      },
      error: e => {
        console.error(e);
      }
    });
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
