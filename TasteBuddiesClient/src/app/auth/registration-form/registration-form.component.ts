import { Component, OnInit } from '@angular/core';
import { Registration } from 'src/models/registration';
import { AuthenticationService } from 'src/services/authentication.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})

export class RegistrationFormComponent implements OnInit {

  regModel: Registration = new Registration('nathan@example.net', 'BATS!', 'password');
  isSignupFailed = false;
  submitted: boolean = false;
  response: any;


  constructor(private authenticationService: AuthenticationService) { }
  
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
