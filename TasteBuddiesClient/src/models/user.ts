export class User {
    id: number;
    email: String;
    displayName: String;
    likes: Array<String>;
    dislikes: Array<String>;
    userLikes: Array<{
        event: number,
        likedRestaurant: Array<string>,
        dislikedRestaurant: Array<string>,
    }>

    constructor(
        id: number, 
        email: String, 
        displayName: String,
        likes: Array<String>,
        dislikes: Array<String>,
        userLikes: Array<{
            event: number,
            likedRestaurant: Array<string>,
            dislikedRestaurant: Array<string>,
        }>,

        ) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.likes = likes;
        this.dislikes = dislikes;
        this.userLikes = userLikes;
    }

}
