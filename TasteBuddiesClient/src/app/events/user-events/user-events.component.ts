import { Component, OnInit } from '@angular/core';
import { User } from 'src/models/user';
import { UserService } from 'src/services/user.service';
import { Event } from 'src/models/event';
import { ActivatedRoute } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { EventService } from 'src/services/event.service';

@Component({
  selector: 'app-user-events',
  templateUrl: './user-events.component.html',
  styleUrls: ['./user-events.component.css']
})
export class UserEventsComponent implements OnInit {

  user: User;
  upcommingEvents: Array<Event> = [new Event(), new Event(), new Event()];
  pastEvents: Array<Event> = [new Event(), new Event(), new Event()];
  // note: trailer $ is a convention for Observables in ng
  events$: Observable<Event[]>;
  selectedID: number;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private eventService: EventService,
    ) {
    this.user = new User(0, '', '');

    this.userService.getUser().subscribe({
      next: res => {
        this.user = new User(res.id, res.email, res.displayName);
      },
      error: (e) => {
        console.error(e);
      },
    });
  }

  ngOnInit(): void {
    this.events$ = this.route.paramMap.pipe(
      switchMap(params => {
        this.selectedID = Number(params.get('id'));
        return this.eventService.getEvents();
      })
    )

    // TODO: write function to break out events into upcoming and past events.

  }

}
