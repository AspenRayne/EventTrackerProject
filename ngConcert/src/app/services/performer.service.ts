import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Performer } from '../models/performer';

@Injectable({
  providedIn: 'root'
})
export class PerformerService {
  private baseUrl = 'http://localhost:8086/';
  private url = this.baseUrl + 'api/concerts';

  constructor(private http: HttpClient) { }

  index(seatGeekId: number): Observable<Performer[]> {
    return this.http.get<Performer[]>(`${this.url}/${seatGeekId}/performers`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'ConcertService.index(): error retrieving concerts: ' + err
            )
        );
      })
    );
  }
}
