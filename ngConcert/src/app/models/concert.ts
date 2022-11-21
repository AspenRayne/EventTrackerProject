import { Performer } from "./performer";
import { Venue } from "./venue";

export class Concert {
  id: number;
  title: string;
  ticketUrl: string;
  concertDate: string;
  seatGeekId: number;
  review: string;
  performers: Performer [];
  venue: Venue;

  constructor(
    id: number = 0,
    title: string = '',
    ticketUrl: string = '',
    concertDate: string = '',
    seatGeekId: number = 0,
    review: string = '',
    performers: [] = [],
    venue: Venue
  ) {
    this.id = id;
    this.title = title;
    this.ticketUrl = ticketUrl;
    this.concertDate = concertDate;
    this.seatGeekId = seatGeekId;
    this.review = review;
    this.performers = performers;
    this.venue = venue;
  }
}
