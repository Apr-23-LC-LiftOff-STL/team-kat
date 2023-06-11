import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventResultRightComponent } from './event-result-right.component';

describe('EventResultRightComponent', () => {
  let component: EventResultRightComponent;
  let fixture: ComponentFixture<EventResultRightComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EventResultRightComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventResultRightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
