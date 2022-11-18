import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Concert } from '../models/concert';

@Injectable({
  providedIn: 'root',
})
export class ConcertService {
  private baseUrl = 'http://localhost:8086/';
  private url = this.baseUrl + 'api/concerts';

  constructor(private http: HttpClient) {}

  index(): Observable<Concert[]> {
    return this.http.get<Concert[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('ConcertService.index(): error retrieving concerts: ' + err)
        );
      })
    );
  }
}
