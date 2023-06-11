import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventResultLeftComponent } from './event-result-left.component';

describe('EventResultLeftComponent', () => {
  let component: EventResultLeftComponent;
  let fixture: ComponentFixture<EventResultLeftComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventResultLeftComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventResultLeftComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
