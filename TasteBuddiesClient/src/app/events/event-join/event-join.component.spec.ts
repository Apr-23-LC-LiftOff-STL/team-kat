import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventJoinComponent } from './event-join.component';

describe('EventJoinComponent', () => {
  let component: EventJoinComponent;
  let fixture: ComponentFixture<EventJoinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventJoinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventJoinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
