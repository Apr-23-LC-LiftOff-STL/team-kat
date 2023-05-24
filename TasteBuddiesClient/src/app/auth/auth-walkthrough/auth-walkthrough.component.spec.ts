import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthWalkthroughComponent } from './auth-walkthrough.component';

describe('AuthWalkthroughComponent', () => {
  let component: AuthWalkthroughComponent;
  let fixture: ComponentFixture<AuthWalkthroughComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthWalkthroughComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthWalkthroughComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
