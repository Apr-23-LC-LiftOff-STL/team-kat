import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/services/authentication.service';
import { StorageService } from 'src/services/storage.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  buttons = [
    {buttonName : "Home", path : "/", class: 'navbar-brand'},
    {buttonName : "Event", path : "/events"},
  ];
  loginLogout = {
    buttonName: "Login", 
    path: "/login",
    navClass: "nav-link"
  };

  loggedIn: boolean = false;

  constructor(
    private storageService: StorageService, 
    private authenticationService: AuthenticationService) {

    // TODO: add the isLoggedIn method to the authentication service?
    this.loggedIn = storageService.isLoggedIn();

    if (this.loggedIn) {
      this.loginLogout = {buttonName : "Sign Out", path : "/", navClass: "nav-link active"}
    }

  }

  ngOnInit(): void {
  }

  logout(): void {
    this.authenticationService.logout();
    this.loggedIn = false;
  }
}
