import { HttpClient } from "@angular/common/http";
import { User } from "../user";
import { EventService } from "src/services/event.service";

export class EventVotingProgress {
    userVotes: Map<String, number> = new Map<String, number>()
    
    constructor(){}
}
