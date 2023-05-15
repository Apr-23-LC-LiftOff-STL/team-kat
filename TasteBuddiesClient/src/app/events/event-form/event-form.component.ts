import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from 'src/models/event';
import { User } from 'src/models/user';
import { EventService } from 'src/services/event.service';
import { UserService } from 'src/services/user.service';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit {
  newEvent: Event = new Event;
  submitted: boolean = false;
  results: Array<User> = [];
  searchBox: any;

  constructor(
    private router: Router,
    private eventService: EventService,
    private userService: UserService,
    ) { 
      // I'll leave this in for the moment, but it won't work because this is 
      // a template driven form. We'd need a reactive form to set values
      // here in the ts.
      this.newEvent.date = new Date(Date.now() + 60000);
      this.searchBox = {
        searchText: '',
      };
    }

  ngOnInit(): void {

  }

  onSubmit(): void {
    this.eventService.createEvent(this.newEvent).subscribe({
      next: res => {
        this.router.navigate(['/event']);
      },
      error: (e) => { 
        console.error(e.message)
      }
    });
  }

  onSelectUser(user: User): void {
    if (!this.newEvent.users.includes(user))  {
      this.newEvent.users.push(user);
      console.log(this.newEvent.users);
    }
  }

  onSearchBoxEntry(searchText: string) {
    if (searchText === '') {
      return;
    }

    console.log(searchText);

    this.userService.searchUsers(searchText).subscribe({
      next: res => {
        this.results = res;
      },
      error: res => {
        console.error(res);
      }
    });

    // this.results = [
    //   new User(1, 'me@new.com', 'Amy'), 
    //   new User(2, 'Hi@he.com', 'Gary')
    // ];
  }

  reloadPage(): void {
    window.location.reload();
  }
}
