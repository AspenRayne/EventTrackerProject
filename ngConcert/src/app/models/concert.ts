export class Concert {
  id: number;
  title: string;
  ticketUrl: string;
  concertDate: string;
  seatGeekId: number;
  review: string;

  constructor(
    id: number = 0,
    title: string = '',
    ticketUrl: string = '',
    concertDate: string = '',
    seatGeekId: number = 0,
    review: string = ''
  ) {
    this.id = id;
    this.title = title;
    this.ticketUrl = ticketUrl;
    this.concertDate = concertDate;
    this.seatGeekId = seatGeekId;
    this.review = review;
  }
}
