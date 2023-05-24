import { User } from "./user";

export class Event {

    id: number = 0;
    location: string = '90210'; // zip code? Address?
    searchRadius: string = '1000';  // meters?
    entryCode: string = 'ABCDEF'; // six characters. Numbers too?
    users: Array<User> = [new User(0, 'demo', 'also demo')];

    // TODO: implement these
    // availableRestaurants: Array<Restauraunt> = new Array();
    // likedRestauraunts: Map<User, Array<Restauraunt>> = new Map();



    constructor(
        id: number = 0,
        location: string = '90210',
        searchRadius: string = '1000', 
        entryCode: string = 'ABCDEF',
        users: Array<User> = [new User(0, 'demo', 'also demo')],
        ) {
        this.id = id;
        this.location = location
        this.searchRadius = searchRadius;
        this.entryCode = entryCode;
    }

}
