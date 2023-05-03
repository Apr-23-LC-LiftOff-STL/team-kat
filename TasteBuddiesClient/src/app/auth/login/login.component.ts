import { Component, OnInit } from '@angular/core';
import { Login } from 'src/models/login';
import { LoginService } from 'src/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  regModel: Login = new Login('bat@batmail.com', 'password')
  submitted: boolean = false;

  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.loginService.postJson(this.regModel).subscribe({
      next: (res) => {
        console.log(res);
        this.submitted = true;
      },
      error: (e) => console.error(e.message)
    });
  }

}
// 