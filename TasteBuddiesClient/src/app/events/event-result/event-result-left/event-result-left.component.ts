import { Component, OnInit } from '@angular/core';
import { EventVotingProgress } from 'src/models/DTO/event-voting-progress';
import { UserVotingProgress } from 'src/models/user-voting-progress';

@Component({
  selector: 'app-event-result-left',
  templateUrl: './event-result-left.component.html',
  styleUrls: ['./event-result-left.component.css']
})
export class EventResultLeftComponent implements OnInit {
  eventVotingProgress: EventVotingProgress
  progresses: UserVotingProgress[] = []
  constructor() { }

  ngOnInit(): void {
    this.eventVotingProgress = new EventVotingProgress;
    for(let [key, value] of this.eventVotingProgress.userVotes){
      let progress: UserVotingProgress = new UserVotingProgress;
      progress.userName = key;
      progress.ratio = (value/this.eventVotingProgress.availableRestaurants)*100;
      this.progresses.push(progress);
    }
  }
}
