import { Component, OnInit } from '@angular/core';
import { Concert } from 'src/app/models/concert';
import { ConcertService } from 'src/app/services/concert.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  concerts: Concert[] = [];

  constructor(private concertService: ConcertService) {}

  ngOnInit(): void {
    this.loadConcerts();
  }

  loadConcerts() {
    this.concertService.index().subscribe({
      next: (concerts) => {
        console.log(concerts);
        this.concerts = concerts;
      },
      error: (fail) => {
        console.error('HomeComponent.loadConcerts: error getting concerts');
        console.error(fail);
      },
    });
  }
}
