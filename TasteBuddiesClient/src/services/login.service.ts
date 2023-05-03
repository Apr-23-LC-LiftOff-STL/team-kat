import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from 'src/models/login';

const baseURL = 'http://localhost:8080/api/auth/authenticate'

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) { }

  create(data: Login): Observable<any> {
    return this.http.put(baseURL, data);
  }

  postJson<T>(data: Login): Observable<T> {
    console.log(JSON.stringify(data))
    return this.http.post<T>(
      baseURL,
      JSON.stringify(data),
      {headers: this.headers}
    )
  }
}
