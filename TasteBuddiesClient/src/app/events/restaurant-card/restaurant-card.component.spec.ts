import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantCardComponent } from './restaurant-card.component';

describe('RestaurantCardComponent', () => {
  let component: RestaurantCardComponent;
  let fixture: ComponentFixture<RestaurantCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RestaurantCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RestaurantCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
