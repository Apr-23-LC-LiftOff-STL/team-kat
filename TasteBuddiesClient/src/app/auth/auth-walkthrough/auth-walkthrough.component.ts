import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/services/authentication.service';
import { StorageService } from 'src/services/storage.service';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-auth-walkthrough',
  templateUrl: './auth-walkthrough.component.html',
  styleUrls: ['./auth-walkthrough.component.css']
})
export class AuthWalkthroughComponent implements OnInit {

  response: string = 'Connecting...';
  users: any;

  constructor(
    private authenticationService: AuthenticationService, 
    private userService: UserService,
    private storageService: StorageService) {
    authenticationService.corsCheck().subscribe({
      next: res => {
        this.response = 'SUCCESS: Connected to API without errors.';
      },
      error: res => {
        console.error(res);
        this.response = 'ERROR: Could not get data from API :(';
      }
    })
  }

  ngOnInit(): void { }

  onClick(): void {
    this.userService.getUsers().subscribe({
      next: res => {
        this.users = res;
      },
      error: e => {
        this.users = 'Error: check the dev tools'
        console.error(e);
      },
    });
  }
  
  clearLogin(): void {
    console.log('Logged out')
    this.authenticationService.logout();
  }

}
