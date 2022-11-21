export class Performer {
  id: number;
  seatGeekId: number;
  name: string;
  imageUrl: string;
  type: string;

  constructor(
    id: number = 0,
    seatGeekId: number = 0,
    name: string,
    imageUrl: string,
    type: string
  ) {
    this.id = id;
    this.seatGeekId = seatGeekId;
    this.name = name;
    this.imageUrl = imageUrl;
    this.type = type;
  }
}
