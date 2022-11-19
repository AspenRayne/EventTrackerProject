import { Component, OnInit } from '@angular/core';
import { Concert } from 'src/app/models/concert';
import { SeatgeekService } from 'src/app/services/seatgeek.service';

@Component({
  selector: 'app-search-seat-geek',
  templateUrl: './search-seat-geek.component.html',
  styleUrls: ['./search-seat-geek.component.css'],
})
export class SearchSeatGeekComponent implements OnInit {
  sgList: Concert[] = [];

  searchParams: { [key: string]: any } = {
    city: '',
    state: '',
    performer: '',
  };

  constructor(private sgService: SeatgeekService) {}

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
    console.log(requestBody);
    this.sgService.index(requestBody).subscribe({
      next: (concerts) => {
        concerts.forEach((element) => {
          this.sgList.push(element);
        });
        console.log(concerts);
      },
    });
  }
}
