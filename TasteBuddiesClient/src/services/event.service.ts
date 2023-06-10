import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { NewEventDTO } from 'src/models/DTO/new-event-dto';
import { UserLikesDTO } from 'src/models/DTO/user-likes-dto';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { EventResultDTO } from 'src/models/DTO/event-result-dto';

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

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

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

  public saveLike(userLikes: UserLikesDTO): Observable<any> {
    return this.http.post(
      EVENT_API + 'like',
      JSON.stringify(userLikes),  //Response Body in Stringified JSON format from the DTO
      httpOptions                 //Sets HTTP headers defined above as Content-Type set to application/json
    );
  }

  public getEventResults(eventId: number): Observable<EventResultDTO> {
    // return this.route.paramMap.pipe(
    //   switchMap((params: ParamMap) => {
    //     const eventId = Number.parseInt(params.get('id')!);
        return this.http.get<EventResultDTO>(`${EVENT_API}${eventId}/result`);
      // })
    // );
  }
}
