import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { EventVotingProgress } from 'src/models/DTO/event-voting-progress';
import { UserVotingProgress } from 'src/models/user-voting-progress';
import { EventService } from 'src/services/event.service';

@Component({
  selector: 'app-event-result-left',
  templateUrl: './event-result-left.component.html',
  styleUrls: ['./event-result-left.component.css']
})
export class EventResultLeftComponent implements OnInit {
  eventVotingProgress: EventVotingProgress = new EventVotingProgress;
  progresses: UserVotingProgress[] = []
  numberOfAvailableRestaurants: number = 0;
  
  constructor(
    private http: HttpClient,
    private eventService: EventService,
  ) { 
  }

  ngOnInit(): void {
    const url = window.location.href;
    const urlParts = url.split("/");
    let eventIdString: string = urlParts[4];
    let eventId: number = parseInt(eventIdString);
    this.eventService.getVotingProgress(eventId).subscribe({
      next: data => {
        let hashData: Map<String, number> = new Map(Object.entries(data));
        this.eventVotingProgress.userVotes = hashData;
        if(this.eventVotingProgress.userVotes.has("Number of Available Restaurants")){
          this.numberOfAvailableRestaurants = this.eventVotingProgress.userVotes.get("Number of Available Restaurants")!;
        } else {
          this.numberOfAvailableRestaurants = 1;
        }
        console.log(this.numberOfAvailableRestaurants);
        for(let [key, value] of this.eventVotingProgress.userVotes){
          if(key === "Number of Available Restaurants"){
            continue;
          }
          let progress: UserVotingProgress = new UserVotingProgress;
          progress.userName = key;
          progress.ratio = Math.round((value/this.numberOfAvailableRestaurants)*100);
          this.progresses.push(progress);
        }
      },
      error: e =>{
        console.error(e);
      }
      
    })
  }
}
