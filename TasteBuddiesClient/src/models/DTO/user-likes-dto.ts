export class UserLikesDTO {
    constructor(
        public userId: string,
        public eventId: string,
        public restaurantId: string
    ) {}
}