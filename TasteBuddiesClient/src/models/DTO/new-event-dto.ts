export class NewEventDTO {

    location: string; // zip code? Address?
    searchRadius: string;  // meters?
    mealTime: Date;

    constructor(
        location: string = '63103',
        // date = new Date("2023/07/01"),
        searchRadius: string = '1000', 
        ) {
            this.location = location;
            // this.date = date;
            this.searchRadius = searchRadius;
        }

}
