export class Venue {
  id: number;
  seatGeekId: number;
  city: string;
  name: string;
  country: string;
  state: string;
  postalCode: string;

  constructor(id: number = 0, seatGeekId: number = 0, city: string = '',
  name: string = '', country: string = '', state: string = '', postalCode: string = ''){
    this.id = id;
    this.seatGeekId = seatGeekId;
    this.city = city;
    this.name = name;
    this.country = country;
    this.state = state;
    this.postalCode = postalCode;
  }
}
