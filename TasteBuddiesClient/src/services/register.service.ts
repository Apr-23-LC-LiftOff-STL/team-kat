import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Registration } from 'src/models/registration';
import { Observable } from 'rxjs';

const baseURL = 'http://localhost:8080/api/auth/register'

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) { }

  create(data: Registration): Observable<any> {
    return this.http.put(baseURL, data);
  }

  postJson<T>(data: Registration): Observable<T> {
    console.log(JSON.stringify(data))
    return this.http.post<T>(
      baseURL,
      JSON.stringify(data),
      {headers: this.headers}
    )
  }

}
