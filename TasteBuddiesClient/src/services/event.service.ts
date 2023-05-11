import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Event } from 'src/models/event';

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

  public createEvent(event: Event): Observable<any> {
    return this.http.post(
      EVENT_API + 'create',
      JSON.stringify(event),
      httpOptions)
  }
}
