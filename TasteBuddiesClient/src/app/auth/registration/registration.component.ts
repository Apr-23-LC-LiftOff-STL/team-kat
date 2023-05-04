import { Component, OnInit } from '@angular/core';
import { Registration } from 'src/models/registration';
import { AuthenticationService } from 'src/services/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  regModel: Registration = new Registration('nathan@example.net', 'BATS!', 'password');
  isSignupFailed = false;
  submitted: boolean = false;
  response: any;


  constructor(private authenticationService: AuthenticationService) {
    authenticationService.corsCheck().subscribe({
      next: res => {
        console.log('Got through CORS!');
        this.response = res;
      },
      error: res => {
        console.error(res);
        this.response = 'failed :/';
      }
    })
  }
  
  ngOnInit(): void {
  }

  onSubmit(): void {
    this.authenticationService.register(this.regModel).subscribe({
      next: res => {
        console.log(res);
        this.isSignupFailed = false;
        this.submitted = true;
      },
      error: e => {
        console.error(e.message);
        this.isSignupFailed = true;
      }
    });
  }

}
