import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SavedConcertsComponent } from './saved-concerts.component';

describe('SavedConcertsComponent', () => {
  let component: SavedConcertsComponent;
  let fixture: ComponentFixture<SavedConcertsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SavedConcertsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SavedConcertsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
