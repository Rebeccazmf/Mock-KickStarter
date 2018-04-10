import { TestBed, inject } from '@angular/core/testing';

import { AdminStartupService } from './admin-startup.service';

describe('AdminStartupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminStartupService]
    });
  });

  it('should be created', inject([AdminStartupService], (service: AdminStartupService) => {
    expect(service).toBeTruthy();
  }));
});
