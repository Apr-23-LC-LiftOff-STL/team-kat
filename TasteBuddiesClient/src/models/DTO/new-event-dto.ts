export class NewEventDTO {

    location: string; // zip code? Address?
    searchRadius: string;  // meters?
    mealTime: Date;

    constructor(
        location: string,
        searchRadius: string, 
        ) {
            this.location = location;
            // this.date = date;
            this.searchRadius = searchRadius;
        }

}
