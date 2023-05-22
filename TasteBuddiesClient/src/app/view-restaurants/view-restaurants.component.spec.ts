import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRestaurantsComponent } from './view-restaurants.component';

describe('ViewRestaurantsComponent', () => {
  let component: ViewRestaurantsComponent;
  let fixture: ComponentFixture<ViewRestaurantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRestaurantsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewRestaurantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
