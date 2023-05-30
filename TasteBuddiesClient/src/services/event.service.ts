import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewEventDTO } from 'src/models/DTO/new-event-dto';

const EVENT_API = 'http://localhost:8080/api/event/';

const httpOptions = {
  headers: new HttpHeaders({
     'Content-Type': 'application/json' 
    })
};

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }

  public createEvent(event: NewEventDTO): Observable<any> {
    return this.http.post(
      EVENT_API + 'create',
      JSON.stringify(event),
      httpOptions)
  }

  public getEvents():Observable<any> {
    return this.http.get(
      EVENT_API + 'all',
    )
  }

  public getEvent(eventId: number):Observable<any> {
    return this.http.post(
      EVENT_API + '',
      JSON.stringify(eventId),
      httpOptions
    )
  }

  public saveLikeOrDislike(liked: boolean, eventId: number): void {
    this.http.post(
      EVENT_API + 'liked',
      JSON.stringify({eventId, liked}),
      httpOptions
    )
  }

}
