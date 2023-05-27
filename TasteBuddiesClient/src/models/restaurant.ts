export class Restaurant {
    id: string;
    name: string;
    address: string;

    constructor(
        id: string = "dummy id string",
        name: string = "dummy name string",
        address: string = "dummy address string"
        ) {
            this.id = id;
            this.name = name;
            this.address = address;
        }
}
