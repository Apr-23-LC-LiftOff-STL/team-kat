import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from 'src/models/login';
import { Registration } from 'src/models/registration';

const AUTH_API = 'http://localhost:8080/api/auth/'

const httpOptions = {
  headers: new HttpHeaders({
     'Content-Type': 'application/json' 
    })
};

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  constructor(private http: HttpClient) { }

  login(credentials: Login): Observable<any> {
    return this.http.post(
      AUTH_API + 'authenticate', 
      JSON.stringify(credentials),
      httpOptions,
    );
  }

  register<T>(data: Registration): Observable<T> {
    console.log(JSON.stringify(data))
    return this.http.post<T>(
      AUTH_API + 'register',
      JSON.stringify(data),
      httpOptions,
    );
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'logout', {}, httpOptions);
  }

}
