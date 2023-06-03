import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Login } from 'src/models/login';
import { Registration } from 'src/models/registration';
import { StorageService } from './storage.service';

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

  constructor(private http: HttpClient, private storageService: StorageService) { }

  login(credentials: Login): Observable<any> {
    return this.http.post(
      AUTH_API + 'authenticate', 
      JSON.stringify(credentials),
      httpOptions,
    );
  }

  register(data: Registration): Observable<any> {
    console.log(JSON.stringify(data))
    return this.http.post(
      AUTH_API + 'register',
      JSON.stringify(data),
      httpOptions,
    );
  }

  logout(): void {
    this.storageService.clearJwt();
  }

  isAuthenticated(): boolean {
    return this.storageService.isLoggedIn();
  }

}
