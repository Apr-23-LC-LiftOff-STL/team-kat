import { Component, OnInit } from '@angular/core';
import { User } from 'src/models/user';
import { UserService } from 'src/services/user.service';
import { Event } from 'src/models/event';

@Component({
  selector: 'app-user-events',
  templateUrl: './user-events.component.html',
  styleUrls: ['./user-events.component.css']
})
export class UserEventsComponent implements OnInit {

  user: User;
  upcommingEvents: Array<Event> = [new Event(), new Event(), new Event()];
  pastEvents: Array<Event> = [new Event(), new Event(), new Event()];

  constructor(private userService: UserService) {
    this.user = new User(0, '', '');

    this.userService.getUser().subscribe({
      next: res => {
        this.user = new User(res.id, res.email, res.displayName);
      },
      error: (e) => {
        console.error(e);
      },
    });
  }

  ngOnInit(): void {
    
  }

}
