import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewEventDTO } from 'src/models/DTO/new-event-dto';

const EVENT_API = 'http://localhost:8080/api/places/';

@Injectable({
  providedIn: 'root'
})

export class PlacesService {

  constructor(private http: HttpClient) { }

  /**
   * 
   * @returns 
   */
  public getRestaurantDetails(placeID: string):Observable<any> {
    return this.http.get(
      EVENT_API + 'restaurant?' +
      'placeID=' + placeID
    )
  }

  public getPhoto(
    photoReference: string, 
    maxwidth?: number, 
    maxheight?: number,
    ):Observable<any> 
  {

    if (maxwidth === undefined && maxheight === undefined) {
      throw Error("Either or both maxwidth or maxheight are required");
    }

    let url = EVENT_API + 'image?' +
    'photo_reference=' + photoReference;

    if (maxwidth! > 0 && maxwidth !== undefined) {
      url += '&maxwidth=' + maxwidth;
    }

    if (maxheight! > 0  && maxheight !== undefined) {
      url += '&maxheight=' + maxheight;
    }

    return this.http.get(url)

  }

}
