import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/models/event';
import { EventService } from 'src/services/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  newEvent: Event = new Event;
  submitted: boolean = false;

  constructor(
    private router: Router,
    private eventService: EventService,
    ) { }

  ngOnInit(): void {

  }

  onSubmit(): void {
    this.eventService.createEvent(this.newEvent).subscribe({
      next: res => {
        this.router.navigate(['/event']);
      },
      error: (e) => { 
        console.error(e.message)
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }


}