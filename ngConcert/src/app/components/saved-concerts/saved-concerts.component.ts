import { Component, OnInit } from '@angular/core';
import { Concert } from 'src/app/models/concert';
import { ConcertService } from 'src/app/services/concert.service';

@Component({
  selector: 'app-saved-concerts',
  templateUrl: './saved-concerts.component.html',
  styleUrls: ['./saved-concerts.component.css'],
})
export class SavedConcertsComponent implements OnInit {

  concerts: Concert[] = [];

  constructor(private concertService: ConcertService) {}

  ngOnInit(): void {
    this.loadConcerts();
  }

  loadConcerts() {
    this.concertService.index().subscribe({
      next: (concerts) => {
        this.concerts = concerts;
      },
      error: (fail) => {
        console.error(
          'SavedConcertsComponent.loadConcerts: error getting concerts'
        );
        console.error(fail);
      }
    });
  }

  addComment(concert: Concert) {
    this.concertService.addComment(concert).subscribe({
      next: (concerts) => {},
      error: (fail) => {
        console.error(
          'SavedConcertsComponent.addComment: error adding comment'
        );
        console.error(fail);
      }
    });
  }

  deleteConcert(id: number) {
    this.concertService.destroy(id).subscribe({
      next: (result) => {
        this.loadConcerts();
      },
      error: (problem) => {
        console.error(
          'SavedConcertsComponent.deleteConcert(): error deleting todo:'
        );
        console.error(problem);
      }
    });
  }
}
