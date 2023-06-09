import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JoinEventDto } from 'src/models/DTO/join-event-dto';
import { EventService } from 'src/services/event.service';
import { HttpClient, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-event-join',
  templateUrl: './event-join.component.html',
  styleUrls: ['./event-join.component.css']
})
export class EventJoinComponent implements OnInit { 
  joinEvent: JoinEventDto = new JoinEventDto('');
  errorMessage: string;

  constructor(
    private router: Router,
    private eventService: EventService,
    private http: HttpClient
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void{
      this.eventService.joinEvent(this.joinEvent.entryCode).subscribe({
        next: res => {
          this.router.navigate(['/event']);
        },
        error: (e) => {
          console.error(e.message);
          this.errorMessage = e.error.message;
        }
      })
  }

}
