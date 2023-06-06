import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewEventDTO } from 'src/models/DTO/new-event-dto';
import { UserLikesDTO } from 'src/models/DTO/user-likes-dto';

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

  public joinEvent(entryCode: string):Observable<any> {
    return this.http.post(
      EVENT_API + 'join',
      JSON.stringify(entryCode),
      httpOptions
    )
  }

  public saveLikedRestaurant(userLikesDTO: UserLikesDTO): Observable<any> {
    return this.http.post(EVENT_API + 'like',
    JSON.stringify(userLikesDTO),//Response Body in Stringified JSON format from the DTO
    httpOptions //Sets HTTP headers defined above as Content-Type set to application/json
    );
  }

  
}
