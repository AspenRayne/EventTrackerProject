import { TestBed } from '@angular/core/testing';

import { SeatgeekService } from './seatgeek.service';

describe('SeatgeekService', () => {
  let service: SeatgeekService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeatgeekService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
