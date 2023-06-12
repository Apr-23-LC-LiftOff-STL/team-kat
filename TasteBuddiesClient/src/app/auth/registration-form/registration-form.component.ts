import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Registration } from 'src/models/registration';
import { AuthenticationService } from 'src/services/authentication.service';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})

export class RegistrationFormComponent implements OnInit {

  regModel: Registration = new Registration('', '', '');
  isSignupFailed = false;
  submitted: boolean = false;
  response: any;


  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    ) { }
  
  ngOnInit(): void {
  }

  onSubmit(): void {

    console.log(this.regModel);

    this.authenticationService.register(this.regModel).subscribe({
      next: res => {
        console.log(res);
        this.isSignupFailed = false;
        this.submitted = true;
        this.router.navigate(['/login']);
      },
      error: e => {
        console.error(e.message);
        this.isSignupFailed = true;
      }
    });
  }

}
