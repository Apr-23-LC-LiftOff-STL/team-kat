import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { EventService } from 'src/services/event.service';
import { EventResultDTO } from 'src/models/DTO/event-result-dto';
import { Observable, switchMap } from 'rxjs';
import { PlacesService } from 'src/services/places.service';

@Component({
  selector: 'app-event-result-right',
  templateUrl: './event-result-right.component.html',
  styleUrls: ['./event-result-right.component.css']
})
export class EventResultRightComponent implements OnInit {
  eventId: number;
  eventResults: EventResultDTO;
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
  isPhotoLoading: boolean = true;

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router,
    private placesService: PlacesService,
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      const eventId = Number.parseInt(params.get('id')!);
      this.eventId = eventId;
      this.getEventResults(eventId);
    });
  }

  getEventResults(eventId: number): void {
    
    this.eventService.getEventResults(eventId).subscribe({
      next: res => {
        this.eventResults = res;
        if (this.eventResults.mutuallyLikedRestaurant) {
          this.getRestaurantDetails(this.eventResults.mutuallyLikedRestaurant);
        }
      },
      error: e => {
        console.error(e);
      }
    })
  }

  getRestaurantDetails(restaurantId: string): void {
    this.placesService.getRestaurantDetails(restaurantId).subscribe({
      next: res => {
        this.restaurantDetails = res;
        this.loadPhoto(this.restaurantDetails.photos[0].photo_reference)
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

  goToEvent(): void {
    const eventIdString = this.eventId.toString();
    this.router.navigate(['/event', eventIdString]);
  }

}
