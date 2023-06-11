import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NewEventDTO } from 'src/models/DTO/new-event-dto';
import { EventService } from 'src/services/event.service';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  newEvent: NewEventDTO = new NewEventDTO('63108', '2', '');

  constructor(
    private router: Router,
    private eventService: EventService,
    ) { 

    }

  ngOnInit(): void {

  }

  onSubmit(): void {
    const formData = new NewEventDTO(
      this.newEvent.location, 
      this.newEvent.searchRadius,
      String(Number.parseFloat(this.newEvent.searchRadius) * 1609.344)
      )

    this.eventService.createEvent(formData).subscribe({
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
