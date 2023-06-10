import { Component, Input, OnInit } from '@angular/core';
import { Event } from 'src/models/event';
import { User } from 'src/models/user';

@Component({
  selector: 'app-user-progress',
  templateUrl: './user-progress.component.html',
  styleUrls: ['./user-progress.component.css']
})
export class UserProgressComponent implements OnInit {

  @Input() user: User;
  @Input() event: Event;
  progress: number;
  
  constructor() { }
  
  ngOnInit(): void {
    this.progress = this.user.likes.length + this.user.dislikes.length;
  }

}
