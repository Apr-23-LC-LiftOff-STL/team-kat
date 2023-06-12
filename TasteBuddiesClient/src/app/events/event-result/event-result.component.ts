import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { EventService } from 'src/services/event.service';
import { EventResultDTO } from 'src/models/DTO/event-result-dto';
import { Observable, switchMap } from 'rxjs';

@Component({
  selector: 'app-event-result',
  templateUrl: './event-result.component.html',
  styleUrls: ['./event-result.component.css']
})
export class EventResultComponent implements OnInit {

  eventId: number;
  eventResults: EventResultDTO; 

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      const eventId = Number.parseInt(params.get('id')!);
      this.getEventResults(eventId);
    });
  }

  getEventResults(eventId: number): void {
    this.eventService.getEventResults(eventId).subscribe({
      next: res => {
        this.eventResults = res;
      },
      error: e => {
        console.error(e);
      }
    })
  }

}
