import { TestBed, inject } from '@angular/core/testing';

import { StartupserviceService } from './startupservice.service';

describe('StartupserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StartupserviceService]
    });
  });

  it('should be created', inject([StartupserviceService], (service: StartupserviceService) => {
    expect(service).toBeTruthy();
  }));
});
