import { TestBed } from '@angular/core/testing';

import { GooglePlacesService } from './google-places.service';

describe('GooglePlacesService', () => {
  let service: GooglePlacesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GooglePlacesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
