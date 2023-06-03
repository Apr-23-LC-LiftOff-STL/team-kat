import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Event } from 'src/models/event';
import { Restaurant } from 'src/models/restaurant';
import { EventService } from 'src/services/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  event$: Observable<any>;
  event: Event;
  currentRestaurant: Restaurant;
  // restaurantIDs: Array<string>;
  restaurants: Array<Restaurant>;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private eventService: EventService,
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

    this.nextRestaurant();
  }

  private nextRestaurant(): void {
    this.currentRestaurant = this.restaurants.shift()!;
  }
}
