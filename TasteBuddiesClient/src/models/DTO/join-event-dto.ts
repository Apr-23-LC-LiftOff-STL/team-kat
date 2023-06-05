export class JoinEventDto {
    entryCode: string;

    constructor(entryCode: string = "ABCDEF"){
        this.entryCode = entryCode;
    }

}
