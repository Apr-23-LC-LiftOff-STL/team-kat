import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Event } from 'src/models/event';
import { EventService } from 'src/services/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  event$: Observable<any>;
  // event: Event = new Event;

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

    // const eventId = this.route.snapshot.paramMap.get('id');

    // if (eventId != null) {
    //   this.event$ = this.eventService.getEvent(Number.parseInt(eventId));
    // }

  }


}
