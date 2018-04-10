import { TestBed, inject } from '@angular/core/testing';

import { StartupHomeService } from './startup-home.service';

describe('StartupHomeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StartupHomeService]
    });
  });

  it('should be created', inject([StartupHomeService], (service: StartupHomeService) => {
    expect(service).toBeTruthy();
  }));
});
