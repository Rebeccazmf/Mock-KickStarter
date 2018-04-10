import { TestBed, inject } from '@angular/core/testing';

import { AdminCreatorService } from './admin-creator.service';

describe('AdminCreatorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminCreatorService]
    });
  });

  it('should be created', inject([AdminCreatorService], (service: AdminCreatorService) => {
    expect(service).toBeTruthy();
  }));
});
