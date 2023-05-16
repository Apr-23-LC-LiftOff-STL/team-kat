export class NewEventDTO {

    location: string; // zip code? Address?
    date: Date;
    searchRadius: string;  // meters?

    constructor(
        location: string = '90210',
        date = new Date("2023/07/01"),
        searchRadius: string = '1000', 
        ) {
            this.location = location;
            this.date = date;
            this.searchRadius = searchRadius;
        }

}
