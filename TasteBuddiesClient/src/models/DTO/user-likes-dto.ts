export class UserLikesDTO {
    constructor(
        public eventId: string,
        public restaurantId: string,
        public isLike: boolean
    ) {}
}