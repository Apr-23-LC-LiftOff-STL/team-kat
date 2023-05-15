import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/user/'

const httpOptions = { 
  headers: new HttpHeaders({
     'Content-Type': 'application/json' 
    })
  };

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(): Observable<any> {
    return this.http.get(API_URL);
  }

  getUsers(): Observable<any> {
    return this.http.get(API_URL + 's');
  }

  searchUsers(term: string): Observable<any> {
    
    return this.http.post(
      API_URL + 'search',
      JSON.stringify({
        term: term
      }),
      httpOptions);
  }

}
