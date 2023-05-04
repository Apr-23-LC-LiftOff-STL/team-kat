import { Component, OnInit } from '@angular/core';
import { Registration } from 'src/models/registration';
import { AuthenticationService } from 'src/services/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  regModel: Registration = new Registration('', '', '');
  isSignupFailed = false;
  submitted: boolean = false;


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
