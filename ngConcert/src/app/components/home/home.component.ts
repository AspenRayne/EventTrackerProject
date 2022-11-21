import { Component, OnInit } from '@angular/core';
import { Concert } from 'src/app/models/concert';
import { ConcertService } from 'src/app/services/concert.service';
import { SeatgeekService } from 'src/app/services/seatgeek.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {




  constructor(
    private concertService: ConcertService,
    private sgService: SeatgeekService
  ) {}

  ngOnInit(): void {

  }





}
