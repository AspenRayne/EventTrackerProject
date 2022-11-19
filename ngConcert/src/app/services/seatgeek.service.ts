import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Concert } from '../models/concert';

@Injectable({
  providedIn: 'root'
})
export class SeatgeekService {
  private baseUrl = 'http://localhost:8086/';
  private url = this.baseUrl + 'api/searchSG/concerts';

  constructor(private http: HttpClient) { }

  index(requestBody: { [key: string]: any } |[]): Observable<Concert[]> {
    return this.http.post<Concert[]>(this.url, requestBody).pipe(
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
