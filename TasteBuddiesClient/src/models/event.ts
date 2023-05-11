import { User } from "./user";

export class Event {

    id: number;
    location: string; // zip code? Address?
    date: Date;
    searchRadius: string;  // meters?
    entryCode: string; // six characters. Numbers too?
    users: Array<User>;

    // TODO: implement these
    // availableRestaurants: Array<Restauraunt> = new Array();
    // likedRestauraunts: Map<User, Array<Restauraunt>> = new Map();



    constructor(
        id: number = 0,
        location: string = '90210',
        date = new Date("2023/07/01"),
        searchRadius: string = '1000', 
        entryCode: string = 'ABCDEF',
        users: Array<User> = [new User(0, 'demo', 'also demo')],
        ) {
            this.id = id;
            this.location = location;
            this.date = date;
            this.searchRadius = searchRadius;
            this.entryCode = entryCode;
            this.users = users;
        }

}