import { Component, OnInit } from '@angular/core';
import { Registration } from 'src/models/registration';
import { RegisterService } from 'src/services/register.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})

export class RegistrationComponent implements OnInit {

  regModel: Registration = new Registration('email', 'display name', 'password')
  submitted: boolean = false;

  constructor(private registerService: RegisterService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.registerService.postJson(this.regModel).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => console.error(e.message)
    });
  }

}
