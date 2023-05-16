import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/models/event';
import { User } from 'src/models/user';
import { EventService } from 'src/services/event.service';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  newEvent: Event = new Event;

  constructor(
    private router: Router,
    private eventService: EventService,
    ) { 
      // I'll leave this in for the moment, but it won't work because this is 
      // a template driven form. We'd need a reactive form to set values
      // here in the ts.
      this.newEvent.date = new Date(Date.now() + 60000);
    }

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
