export class User {
    id: number;
    email: String;
    displayName: String;

    constructor(
        id: number = -1, 
        email: String = 'falcor@example.com', 
        displayName: String = 'BATS'
        ) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }

}
