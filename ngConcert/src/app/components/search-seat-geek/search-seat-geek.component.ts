import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Concert } from 'src/app/models/concert';
import { Performer } from 'src/app/models/performer';
import { ConcertService } from 'src/app/services/concert.service';
import { PerformerService } from 'src/app/services/performer.service';
import { SeatgeekService } from 'src/app/services/seatgeek.service';
import { SavedConcertsComponent } from '../saved-concerts/saved-concerts.component';

@Component({
  selector: 'app-search-seat-geek',
  templateUrl: './search-seat-geek.component.html',
  styleUrls: ['./search-seat-geek.component.css'],
})
export class SearchSeatGeekComponent implements OnInit {
  sgList: Concert[] = [];

  searchParams: { [key: string]: any } = this.resetForm();

  constructor(
    private sgService: SeatgeekService,
    private concertService: ConcertService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  searchSG() {
    let requestBody = [];
    for (let p in this.searchParams) {
      if (this.searchParams[p]) {
        requestBody.push({
          type: p,
          query: this.searchParams[p],
        });
      }
    }
    this.sgService.index(requestBody).subscribe({
      next: (concerts) => {
        concerts.forEach((element) => {
          this.sgList.push(element);
        });
      },
    });
    this.sgList = [];
    this.searchParams = this.resetForm();
  }

  resetForm(): { [key: string]: any } {
    return {
      city: '',
      state: '',
      performer: '',
    };
  }

  saveConcert(concert: Concert) {
    this.concertService.create(concert).subscribe({
      next: (concerts) => {
        this.router.navigateByUrl('/concerts')
      },
    });
  }
}
