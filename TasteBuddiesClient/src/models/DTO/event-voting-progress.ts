import { User } from "../user";

export class EventVotingProgress {
    userVotes: Map<String, number> = new Map<String, number>([
        ["User1", 26],
        ["User2", 24],
        ["User3", 14]
    ]);
    availableRestaurants: number = 30; 
    
    constructor(){
        // this.userLikes = userLikes;
        // this.userDislikes = userDislikes;
        // this.availableRestaurants = availablerestaurants;
    }
    
}
