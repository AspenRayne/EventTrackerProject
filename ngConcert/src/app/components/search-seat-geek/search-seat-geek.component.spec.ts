import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchSeatGeekComponent } from './search-seat-geek.component';

describe('SearchSeatGeekComponent', () => {
  let component: SearchSeatGeekComponent;
  let fixture: ComponentFixture<SearchSeatGeekComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchSeatGeekComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchSeatGeekComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
